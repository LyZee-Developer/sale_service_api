package org.commerce.sale_management.module.user_service.service.impl;

import org.commerce.sale_management.module.user_service.dto.AuthDTO;
import org.commerce.sale_management.module.user_service.dto.UserDTO;
import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.repository.UserRepository;
import org.commerce.sale_management.module.user_service.request.auth.AuthRequest;
import org.commerce.sale_management.module.user_service.service.AuthService;
import org.commerce.sale_management.security.CurrentUser;
import org.commerce.sale_management.security.JwtTokenService;
import org.module.publish_service.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private CurrentUser currentUser;

    // compared against when the username is unknown, so that path costs the same as a wrong password
    private String dummyHash;

    @PostConstruct
    void init() {
        dummyHash = passwordEncoder.encode("unknown-user-placeholder");
    }

    @Override
    public Optional<AuthDTO.token> login(AuthRequest.login req) {
        Optional<User> user = userRepository.findByUsername(req.getUsername());
        if (user.isEmpty()) {
            passwordEncoder.matches(req.getPassword(), dummyHash);
            return Optional.empty();
        }
        User found = user.get();
        if (!Boolean.TRUE.equals(found.getIsActivate()) || !passwordEncoder.matches(req.getPassword(), found.getPassword())) {
            return Optional.empty();
        }
        return Optional.of(new AuthDTO.token(jwtTokenService.issue(found), "Bearer",
                jwtTokenService.expiresInSeconds(), found.getUsername(), found.getRole()));
    }

    @Override
    public UserDTO.view me() {
        return userRepository.findById(currentUser.id())
                .map(UserDTO.view::new)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
