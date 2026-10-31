package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.constant.OrderConst;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:15 PM
 */
@Entity
@AllArgsConstructor
@Table(name = "orders")
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Order extends BasePrimaryIdEntity {
    @NotBlank
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderConst.Status status;

    @Enumerated(EnumType.STRING)
    private OrderConst.PaymentStatus paymentStatus;

    @NotNull
    LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DinningTable table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "code")
    private Customer customer;

    @OneToMany(mappedBy = OrderItem_.ORDER, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orders = new ArrayList<>();

    @OneToMany(mappedBy = OrderItem_.ORDER, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderHistory> orderHistories = new ArrayList<>();

    @OneToMany(mappedBy = OrderItem_.ORDER, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<KitchenTicket> kitchenTickets;

}
