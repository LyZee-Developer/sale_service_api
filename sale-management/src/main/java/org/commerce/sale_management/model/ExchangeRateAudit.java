package org.commerce.sale_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 8:30 PM
 */
@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_cur_code", referencedColumnName = "code")
    private Currency currencyFrom;

    @ManyToOne
    @JoinColumn(name = "to_cur_code", referencedColumnName = "code")
    private Currency currencyTo;
    private Double amountFrom;
    private Double amountTo;
    private LocalDate date;
    private Double calcAmount;

    @ManyToOne
    @JoinColumn
    private ExchangeRate exchangeRate;

    @ManyToOne
    @JoinColumn
    private Employee createdByEmployee;

    public ExchangeRateAudit(ExchangeRate exchangeRate) {
        this.currencyFrom = exchangeRate.getCurrencyFrom();
        this.amountFrom = exchangeRate.getAmountFrom();
        this.currencyTo = exchangeRate.getCurrencyTo();
        this.amountTo = exchangeRate.getAmountTo();
        this.date = exchangeRate.getDate();
        this.createdByEmployee = exchangeRate.getCreatedByEmployee();
        this.calcAmount = exchangeRate.getCalcAmount();
        this.exchangeRate = exchangeRate;
    }

}
