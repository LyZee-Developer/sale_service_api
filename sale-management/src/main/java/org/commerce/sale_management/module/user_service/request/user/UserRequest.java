package org.commerce.sale_management.module.user_service.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.module.user_service.model.UserAddress;

import java.util.List;

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
        @NotBlank(message = "field username is required!")
        private String username;

        @NotBlank(message = "field password is required!")
        private String password;

        @NotNull
        private List<UserAddressRequest> userAddress;
    }

    @Getter
    @Setter
    public static class UserAddressRequest {
        private String facebook;
        private String email;
        private String phone;
        private String phone1;
        private String address;
    }

    @Getter
    @Setter
    public static class update extends save{
        @Positive
        @NotNull
        private Long id;
    }

}
