package org.commerce.sale_management.vo.currency;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.constant.RequiredFieldConstant;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 6:54 PM
 */
@Setter
@Getter
public class CurrencyRequest {
    private Long id;
    @NotBlank(message = RequiredFieldConstant.NAME)
    private String name;
    private String nameEn;
    /*
     * symbol of currency
     * @value $, ៛
     */
    @NotBlank(message = RequiredFieldConstant.SYMBOL)
    @Length(max = 1, message = "Symbol must be 1 letter.")
    private String symbol;
    /*
     * Code of currency
     * @value USD, KHR
     */
    @NotBlank(message = RequiredFieldConstant.CODE)
    private String code;
    private BigDecimal decimalPlaces;
    private Boolean isBase;
}
