package org.commerce.sale_management.module.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.commerce.sale_management.module.user_service.constant.UserRole;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
public class AuthDTO {

    @Getter
    @AllArgsConstructor
    public static class token {
        private String accessToken;
        private String tokenType;
        private long expiresIn;
        private String username;
        private UserRole role;
    }
}
