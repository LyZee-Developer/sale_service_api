package org.commerce.sale_management.vo.menuItem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 5:46 AM
 */
@Setter
@Getter
public class MenuItemRequest {
    private Long id;
    @NotBlank(message = "name is required.")
    private String name;
    private String nameEn;
    private Boolean isAvailable = Boolean.TRUE;
    private BigDecimal price;
    private Double discount = (double) 0;
    private String description;
    private String descriptionEn;
    private Long categoryId;
}
