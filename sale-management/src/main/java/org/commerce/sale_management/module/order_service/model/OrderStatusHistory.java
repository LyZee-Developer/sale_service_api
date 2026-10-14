package org.commerce.sale_management.module.order_service.model;

import jakarta.persistence.*;
import java.time.Instant;
import lombok.*;
import org.commerce.sale_management.module.order_service.constant.OrderStatus;
import org.commerce.sale_management.module.user_service.model.User;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "order_status_history")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusHistory extends BasePrimaryIdEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private OrderStatus oldStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OrderStatus newStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "changed_by")
    private User changedBy;

    @Column(nullable = false)
    private Instant changedAt;

    @Column(columnDefinition = "TEXT")
    private String note;
}
