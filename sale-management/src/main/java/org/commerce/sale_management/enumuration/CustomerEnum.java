package org.commerce.sale_management.enumuration;

import lombok.Getter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 7:24 PM
 */
public interface CustomerEnum {
    @Getter
    enum Type {
        GENERAL("General"),
        STAFF("Staff");
        private final String description;

        Type(String description) {
            this.description = description;
        }
    }

}
