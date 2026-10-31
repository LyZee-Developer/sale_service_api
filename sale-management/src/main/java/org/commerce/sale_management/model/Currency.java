package org.commerce.sale_management.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

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
    @NotBlank
    private String nameKh;
    @NotBlank
    private String symbol; //-- $, ៛
    @NotBlank
    private String code; //-- USD, KHR
    private Double decimalPlaces;
    private String nameEn;
    private Boolean isBase;

}
