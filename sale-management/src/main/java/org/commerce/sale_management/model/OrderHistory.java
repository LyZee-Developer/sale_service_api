package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.time.LocalDateTime;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/27/2026 7:00 PM
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderHistory extends BasePrimaryIdEntity {

    private String oldStatus;
    @NotBlank
    private String newStatus;

    private String oldPaymentStatus;
    @NotBlank
    private String newPaymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonBackReference
    private Order order;
}
