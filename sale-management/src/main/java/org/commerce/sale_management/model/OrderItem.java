package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.commerce.sale_management.enumuration.KitchenTicketEnum;
import org.commerce.sale_management.enumuration.OrderEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:24 PM
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MenuItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Order order;

    private Boolean isCountIn = Boolean.TRUE;

    @Min(1)
    @Positive
    private Integer qty;
    private BigDecimal unitPrice;
    
    @NotBlank(message = "code is required")
    private String code;

    @Enumerated(EnumType.STRING)
    private KitchenTicketEnum.Status status = KitchenTicketEnum.Status.WAITING;

    @Column(columnDefinition = "Text")
    private String specialInstructions;

}
