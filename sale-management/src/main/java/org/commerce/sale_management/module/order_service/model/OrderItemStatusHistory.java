package org.commerce.sale_management.module.order_service.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;
import org.commerce.sale_management.module.order_service.constant.KitchenStatus;
import org.commerce.sale_management.module.user_service.model.User;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "order_item_status_history")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemStatusHistory extends BasePrimaryIdEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private KitchenStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private KitchenStatus newStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(nullable = false)
    private Instant changedAt;

    @Column(columnDefinition = "TEXT")
    private String note;
}
