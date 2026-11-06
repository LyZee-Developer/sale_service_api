package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.commerce.sale_management.constant.RequiredFieldConstant;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "from_cur_code", referencedColumnName = "code")
    private Currency currencyFrom;

    @ManyToOne
    @JoinColumn(name = "to_cur_code", referencedColumnName = "code")
    private Currency currencyTo;
    private Double amountFrom;
    private Double amountTo;
    private Double calcAmount;
    private LocalDate date;

    @OneToMany(mappedBy = "exchangeRate")
    @JsonIgnore
    List<ExchangeRateAudit> exchangeRateAudits = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private Employee createdByEmployee;

    public Double calculateAmount() {
        if (this.amountTo == null || this.amountTo == 0) {
            // Handle zero case safely (e.g., log, throw custom message, or return 0)
            throw new ApiResponseHandler("The to amount must be over 0.");
        } else {
            BigDecimal value = new BigDecimal(this.amountTo / this.amountFrom);
            BigDecimal result = value.setScale(6, RoundingMode.HALF_UP);
            return result.doubleValue();
        }
    }
}
