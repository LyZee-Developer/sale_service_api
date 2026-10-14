package org.commerce.sale_management.module.menu_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "menu_items")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem extends BasePrimaryIdEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private MenuCategory category;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isAvailable = Boolean.TRUE;
}
