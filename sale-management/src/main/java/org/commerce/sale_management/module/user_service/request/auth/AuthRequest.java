package org.commerce.sale_management.module.user_service.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
public class AuthRequest {

    @Getter
    @Setter
    public static class login {
        @NotBlank(message = "field username is required!")
        private String username;

        @NotBlank(message = "field password is required!")
        private String password;
    }
}
