package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.math.BigDecimal;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 7:35 PM
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends BasePrimaryIdEntity {
    @NotBlank
    private String nameKh;
    private String nameEn;
    private Boolean isAvailable;
    private BigDecimal price;
    private Double discount;
    private String descriptionEn;
    private String descriptionKh;

    @ManyToOne
    @JsonBackReference
    private MenuCategory category;
}
