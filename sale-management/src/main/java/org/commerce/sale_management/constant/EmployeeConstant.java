package org.commerce.sale_management.constant;

import lombok.Getter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 6:13 AM
 */
public interface EmployeeConstant {
    @Getter
    enum Status {
        ACTIVE("Active"),
        INACTIVE("Inactive");

        private final String description;

        Status(String description) {
            this.description = description;
        }

    }
}
