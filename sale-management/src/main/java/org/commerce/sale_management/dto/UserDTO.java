package org.commerce.sale_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commerce.sale_management.model.UserTesting;

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

        public view(UserTesting user) {
            this.username = user.getUsername();
            this.id = user.getId();
            this.password = user.getPassword();
        }
    }

    /** Cached in Redis (JSON) - keep it free of password and lazy JPA relations. */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class detail  {
        private Long id;
        private String username;

        public detail(UserTesting user) {
            this.id = user.getId();
            this.username = user.getUsername();
        }
    }
}
