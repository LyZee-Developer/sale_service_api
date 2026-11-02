package org.commerce.sale_management.dto;

import lombok.Setter;
import org.commerce.sale_management.model.MenuItem;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 5:54 AM
 */
@Setter
public class MenuItemDTO extends MenuItem implements BaseView {
    public MenuItemDTO(MenuItem org) {
        this.copyAllIncludeProperties(org);
    }
}
