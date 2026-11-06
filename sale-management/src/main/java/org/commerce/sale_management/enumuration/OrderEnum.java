package org.commerce.sale_management.enumuration;

import lombok.Getter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:19 PM
 */
public interface OrderEnum {
    @Getter
    enum Status {
        START("Start"), END("End");

        private final String description;

        Status(String description) {
            this.description = description;
        }
    }
}
