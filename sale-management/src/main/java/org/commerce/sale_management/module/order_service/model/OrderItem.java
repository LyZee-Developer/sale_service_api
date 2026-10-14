package org.commerce.sale_management.module.order_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;
import org.commerce.sale_management.module.menu_service.model.MenuItem;
import org.commerce.sale_management.module.order_service.constant.KitchenStatus;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "order_items")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BasePrimaryIdEntity {
    @Version
    @Column(nullable = false)
    private Long version;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    // order-time snapshots: do not change when the menu item is renamed or repriced
    @Column(nullable = false, length = 150)
    private String itemName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal lineTotal;

    @Column(columnDefinition = "TEXT")
    private String specialInstructions;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private KitchenStatus kitchenStatus;
}
