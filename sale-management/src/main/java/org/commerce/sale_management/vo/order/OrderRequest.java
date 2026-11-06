package org.commerce.sale_management.vo.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.constant.OrderConstant;
import org.commerce.sale_management.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 10:32 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long id;
    private String orderNumber;
    private String status;
    private String customerType;
    private String paymentStatus;
    private Long tableId;
    private String customerCode;
    @NotEmpty
    private List<OrderItemRequest> orderItems = new ArrayList<>();

    @Getter
    @Setter
    public static class OrderItemRequest {
        private Long id;
        private Long itemId;
        private Boolean isCountIn = Boolean.TRUE;
        @Min(1)
        @Positive
        private Integer qty;
        private BigDecimal unitPrice;
        private String specialInstructions;
    }
}
