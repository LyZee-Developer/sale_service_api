package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
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
    @JsonIncludeProperties({
        "id",
        "name",
        "nameEn",
        "isAvailable",
        "discount",
        "description",
        "descriptionEn",
    })
    private MenuItem item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Order order;

    @Builder.Default
    private Boolean isCountIn = Boolean.TRUE;

    @Min(1)
    @Positive
    private Integer qty;
    private BigDecimal unitPrice;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private KitchenTicketEnum.Status status = KitchenTicketEnum.Status.WAITING;

    @Column(columnDefinition = "Text")
    private String specialInstructions;

}
