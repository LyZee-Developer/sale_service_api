package org.commerce.sale_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/27/2026 5:44 AM
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExchangeRate extends BasePrimaryIdEntity {
    @ManyToOne
    @JoinColumn
    private Currency currencyFrom;
    @ManyToOne
    @JoinColumn
    private Currency currencyTo;
    private BigDecimal amount;
    private LocalDate currencyDate;
}
