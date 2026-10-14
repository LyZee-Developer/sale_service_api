package org.commerce.sale_management.module.user_service.controller;

import org.commerce.sale_management.module.user_service.constant.RouteConst;
import org.commerce.sale_management.module.user_service.request.auth.AuthRequest;
import org.commerce.sale_management.module.user_service.service.AuthService;
import org.module.publish_service.response.ApiErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
@RestController
@RequestMapping(RouteConst.AUTH)
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthRequest.login data) {
        return authService.login(data)
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorResponse(
                        HttpStatus.UNAUTHORIZED, "Invalid username or password", LocalDateTime.now(), null)));
    }

    @GetMapping("/me")
    public ResponseEntity<Object> me() {
        return ResponseEntity.ok(authService.me());
    }
}
