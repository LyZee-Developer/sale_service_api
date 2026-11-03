package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.constant.OrderConstant;
import org.hibernate.annotations.Audited;
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
@Audited
public class Order extends BasePrimaryIdEntity {
    @NotBlank
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    private OrderConstant.Status status;

    @Enumerated(EnumType.STRING)
    private CustomerConstant.Type customerType;

    @Enumerated(EnumType.STRING)
    private OrderConstant.PaymentStatus paymentStatus;

    @NotNull
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DinningTable table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "code")
    private Customer customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @NotEmpty
    private List<OrderItem> orders = new ArrayList<>();

//    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private List<OrderHistory> orderHistories = new ArrayList<>();

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<KitchenTicket> kitchenTickets;

}
