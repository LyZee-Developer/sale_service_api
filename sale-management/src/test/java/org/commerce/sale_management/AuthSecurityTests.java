package org.commerce.sale_management;

import com.jayway.jsonpath.JsonPath;
import org.commerce.sale_management.module.user_service.constant.UserRole;
import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Phase 1b: JWT login, role-based access and audit username. Each test rolls back.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AuthSecurityTests {

    private static final String PASSWORD = "Secret#123";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void loginReturnsTokenThatAuthenticatesMe() throws Exception {
        user("admin-a", UserRole.ADMIN, true);
        String token = login("admin-a");

        mvc.perform(get("/api/auth/me").header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin-a"))
                .andExpect(jsonPath("$.role").value("ADMIN"))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    void wrongPasswordIsUnauthorized() throws Exception {
        user("admin-b", UserRole.ADMIN, true);
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"admin-b\",\"password\":\"wrong\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.accessToken").doesNotExist());
    }

    @Test
    void unknownUserIsUnauthorized() throws Exception {
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"nobody\",\"password\":\"" + PASSWORD + "\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void disabledUserCannotLogin() throws Exception {
        user("waiter-off", UserRole.WAITER, false);
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"waiter-off\",\"password\":\"" + PASSWORD + "\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void blankLoginFieldsAreBadRequest() throws Exception {
        mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void requestWithoutTokenIsUnauthorized() throws Exception {
        mvc.perform(get("/api/user")).andExpect(status().isUnauthorized());
        mvc.perform(get("/api/auth/me")).andExpect(status().isUnauthorized());
    }

    @Test
    void invalidTokenIsUnauthorized() throws Exception {
        mvc.perform(get("/api/auth/me").header("Authorization", "Bearer not-a-real-token"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void nonAdminCannotManageUsers() throws Exception {
        user("waiter-c", UserRole.WAITER, true);
        mvc.perform(get("/api/user").header("Authorization", "Bearer " + login("waiter-c")))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminCreatesUserAndAuditRecordsAdminUsername() throws Exception {
        user("admin-d", UserRole.ADMIN, true);
        String token = login("admin-d");

        mvc.perform(post("/api/user").header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"username":"cashier-d","password":"Pass#1234","fullName":"Cashier D",
                                 "role":"CASHIER","userAddress":[]}
                                """))
                .andExpect(status().isOk());

        assertEquals("admin-d", jdbc.queryForObject(
                "select created_by from app_user where username = 'cashier-d'", String.class));
        User created = userRepository.findByUsername("cashier-d").orElseThrow();
        assertEquals(UserRole.CASHIER, created.getRole());
        assertEquals(true, passwordEncoder.matches("Pass#1234", created.getPassword()));
    }

    // ----- fixtures -----

    private void user(String username, UserRole role, boolean active) {
        User user = User.builder().username(username).password(passwordEncoder.encode(PASSWORD))
                .fullName("Test " + username).role(role).build();
        user.setIsActivate(active);
        userRepository.saveAndFlush(user);
    }

    private String login(String username) throws Exception {
        String body = mvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + username + "\",\"password\":\"" + PASSWORD + "\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andReturn().getResponse().getContentAsString();
        return JsonPath.read(body, "$.accessToken");
    }
}
