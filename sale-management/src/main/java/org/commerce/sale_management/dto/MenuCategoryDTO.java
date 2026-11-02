package org.commerce.sale_management.dto;

import lombok.Getter;
import org.commerce.sale_management.model.MenuCategory;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/2/2026 5:58 AM
 */
@Getter
public class MenuCategoryDTO extends MenuCategory implements BaseView {
    public MenuCategoryDTO(MenuCategory org) {
        this.copyAllIncludeProperties(org);
    }
}
