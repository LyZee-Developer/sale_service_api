package org.commerce.sale_management.service;

import org.commerce.sale_management.model.MenuItem;
import org.commerce.sale_management.specification.filter.MenuItemRequestFilter;
import org.commerce.sale_management.vo.menuItem.MenuItemRequest;
import org.module.publish_service.service.BaseInternalService;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 5:56 AM
 */
public interface MenuItemService extends BaseInternalService<MenuItem, Long, MenuItemRequestFilter> {
    MenuItem save(MenuItemRequest data);

    MenuItem update(MenuItemRequest data);

    String updateStatus(Long id, boolean isActive);
}
