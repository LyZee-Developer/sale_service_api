package org.commerce.sale_management.enumuration;

import lombok.Getter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:19 PM
 */
public interface PaymentEnum {
    @Getter
    enum PaymentStatus {
        UNPAID("Unpaid"), PAID("Paid");

        private final String description;

        PaymentStatus(String description) {
            this.description = description;
        }
    }
}
