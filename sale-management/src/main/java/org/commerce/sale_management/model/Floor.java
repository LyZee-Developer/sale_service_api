package org.commerce.sale_management.model;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 10:43 PM
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Floor extends BasePrimaryIdEntity {
    @NotBlank
    @Column(unique = true)
    private String name;
    private String nameEn;
    private String descriptionEn;
    private String description;
}
