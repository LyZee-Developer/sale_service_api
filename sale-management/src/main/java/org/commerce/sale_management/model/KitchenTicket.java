package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.constant.KitchenTicketConstant;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:41 PM
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KitchenTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tickerNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private DinningTable table;

    @NotNull
    private LocalDateTime sentAt;

    private LocalDateTime printedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private KitchenTicketConstant.Status status;

    @OneToMany(mappedBy = "kitchenTicket", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    @OrderBy("id desc")
    private List<KitchenTicketHistory> kitchenTicketsHistories;
}
