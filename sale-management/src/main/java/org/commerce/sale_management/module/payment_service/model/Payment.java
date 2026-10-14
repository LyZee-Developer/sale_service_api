package org.commerce.sale_management.module.payment_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.*;
import org.commerce.sale_management.module.order_service.model.Order;
import org.commerce.sale_management.module.payment_service.constant.PaymentMethod;
import org.commerce.sale_management.module.payment_service.constant.PaymentStatus;
import org.commerce.sale_management.module.user_service.model.User;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */
@Entity
@Getter
@Table(name = "payments")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BasePrimaryIdEntity {
    @Version
    @Column(nullable = false)
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod paymentMethod;

    // status of this payment record, not the order's aggregate payment status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    @Column(length = 150)
    private String transactionReference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "received_by")
    private User receivedBy;

    private Instant paidAt;
}
