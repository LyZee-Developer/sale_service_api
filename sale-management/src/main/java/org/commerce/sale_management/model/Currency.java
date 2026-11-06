package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.commerce.sale_management.constant.RequiredFieldConstant;
import org.hibernate.validator.constraints.Length;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/27/2026 5:37 AM
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency extends BasePrimaryIdEntity {
    @NotBlank(message = RequiredFieldConstant.NAME)
    @Column(unique = true)
    private String name;
    private String nameEn;

    //-- $, ៛
    @NotBlank(message = RequiredFieldConstant.SYMBOL)
    @Column(unique = true)
    private String symbol;

    //-- USD, KHR
    @NotBlank(message = RequiredFieldConstant.CODE)
    @Column(length = 10, unique = true)
    private String code;
    @Column(precision = 19, scale = 2)
    private BigDecimal decimalPlaces;
    private Boolean isBase;
}
