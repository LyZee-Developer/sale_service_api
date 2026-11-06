package org.commerce.sale_management.vo.exchangeRate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 8:41 PM
 */
@Setter
@Getter
public class ExchangeRateRequest {
    private Long id;
    @NotBlank(message = "currency from is required")
    private String currencyFrom;
    @NotNull(message = "amount to is required.")
    private Double amountFrom;
    @NotBlank(message = "currency to is required.")
    private String currencyTo;
    @NotNull(message = "amount to is required.")
    private Double amountTo;
    private Long employeeId;
}
