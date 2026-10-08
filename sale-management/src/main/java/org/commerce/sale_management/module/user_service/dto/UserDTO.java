package org.commerce.sale_management.module.user_service.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.module.user_service.model.User;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:32 PM
 */
public class UserDTO {

    @Getter
    @Setter
    public static class view {
        private Long id;
        private String username;
        private String password;

        public view(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public view(User user) {
            this.username = user.getUsername();
            this.id = user.getId();
            this.password = user.getPassword();
        }
    }
}
