package org.commerce.sale_management.enumuration;

import lombok.Getter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:46 PM
 */
public interface KitchenTicketEnum {
    @Getter
    enum Status {
        WAITING("Waiting"),
        IN_PROGRESS("In progress"),
        COMPLETE("Completed");

        private final String description;

        Status(String description) {
            this.description = description;
        }

    }
}
