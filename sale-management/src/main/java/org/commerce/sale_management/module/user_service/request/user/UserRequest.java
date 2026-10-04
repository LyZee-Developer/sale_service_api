package org.commerce.sale_management.module.user_service.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:20 PM
 */
@Setter
@Getter
public class UserRequest {

    @Getter
    @Setter
    public static class save {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }

    @Getter
    @Setter
    public static class update extends save{
        @Positive
        @NotNull
        private Long id;
    }

}
