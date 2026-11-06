package org.commerce.sale_management.vo.floor;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.constant.RequiredFieldConstant;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 10:49 PM
 */
@Setter
@Getter
public class FloorRequest {
    private Long id;
    @NotBlank(message = RequiredFieldConstant.NAME)
    private String name;
    private String nameEn;
    private String descriptionEn;
    private String description;
}
