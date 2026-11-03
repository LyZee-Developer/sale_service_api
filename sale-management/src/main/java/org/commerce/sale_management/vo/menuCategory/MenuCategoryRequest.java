package org.commerce.sale_management.vo.menuCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.constant.RequiredFieldConstant;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/2/2026 5:50 AM
 */
@Setter
@Getter
public class MenuCategoryRequest {
    private Long id;
    @NotBlank(message = RequiredFieldConstant.NAME)
    private String name;
    private String nameEn;
    private String description;
    private String descriptionEn;
}
