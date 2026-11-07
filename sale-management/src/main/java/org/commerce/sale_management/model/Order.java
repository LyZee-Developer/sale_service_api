package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.constant.OrderConstant;
import org.commerce.sale_management.enumuration.CustomerEnum;
import org.commerce.sale_management.enumuration.OrderEnum;
import org.commerce.sale_management.enumuration.PaymentEnum;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.math.BigDecimal;
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
    private OrderEnum.Status status;

    @Enumerated(EnumType.STRING)
    private CustomerEnum.Type customerType;

    @Enumerated(EnumType.STRING)
    private PaymentEnum.PaymentStatus paymentStatus;

    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DinningTable table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_code", referencedColumnName = "code")
    private Customer customer;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderItem> orders;

}
