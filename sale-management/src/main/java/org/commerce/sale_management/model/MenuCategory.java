package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 7:32 PM
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategory extends BasePrimaryIdEntity {
    @NotBlank
    private String nameKh;
    private String nameEn;
    @NotBlank
    private String descriptionKh;
    private String descriptionEn;

    @OneToMany(mappedBy = MenuItem_.CATEGORY, orphanRemoval = true)
    @JsonManagedReference
    private List<MenuItem> items;
}
