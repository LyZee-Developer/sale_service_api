package org.commerce.sale_management.module.menu_service.model;

import jakarta.persistence.*;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "menu_categories")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuCategory extends BasePrimaryIdEntity {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;
}
