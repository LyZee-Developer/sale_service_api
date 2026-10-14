package org.commerce.sale_management.security;

import lombok.extern.slf4j.Slf4j;
import org.commerce.sale_management.module.user_service.constant.UserRole;
import org.commerce.sale_management.module.user_service.repository.UserRepository;
import org.commerce.sale_management.module.user_service.request.user.UserRequest;
import org.commerce.sale_management.module.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * Creates the first ADMIN from BOOTSTRAP_ADMIN_USERNAME / BOOTSTRAP_ADMIN_PASSWORD when no admin exists,
 * so the user endpoints (ADMIN only) can be reached on a fresh database.
 *
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
@Slf4j
@Component
public class AdminBootstrap implements ApplicationRunner {

    @Autowired
    private BootstrapAdminProperties properties;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) {
        if (!StringUtils.hasText(properties.username()) || !StringUtils.hasText(properties.password())) {
            if (!userRepository.existsByRole(UserRole.ADMIN)) {
                log.warn("No ADMIN user exists. Set BOOTSTRAP_ADMIN_USERNAME and BOOTSTRAP_ADMIN_PASSWORD to create one.");
            }
            return;
        }
        if (userRepository.existsByRole(UserRole.ADMIN)) {
            return;
        }
        UserRequest.save req = new UserRequest.save();
        req.setUsername(properties.username());
        req.setPassword(properties.password());
        req.setFullName("Administrator");
        req.setRole(UserRole.ADMIN);
        req.setUserAddress(Collections.emptyList());
        userService.save(req);
        log.info("Bootstrap ADMIN user '{}' created", properties.username());
    }
}
