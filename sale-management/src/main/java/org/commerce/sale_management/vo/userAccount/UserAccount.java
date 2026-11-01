package org.commerce.sale_management.vo.userAccount;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/31/2026 10:11 PM
 */
@Getter
@Setter
public class UserAccount {

    @Setter
    @Getter
    public static class login {
        @NotBlank(message = "username is required.")
        private String username;
        @NotBlank(message = "username is required.")
        private String password;
    }

    @Setter
    @Getter
    public static class create extends login {
        @Positive
        @NotNull
        private Long employeeId;
    }

    @Setter
    @Getter
    public static class update extends create {
        @Positive
        private Long id;
    }

    @Setter
    @Getter
    public static class reset extends login {
    }

}
