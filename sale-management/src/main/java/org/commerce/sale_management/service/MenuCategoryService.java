package org.commerce.sale_management.service;

import org.commerce.sale_management.model.MenuCategory;
import org.commerce.sale_management.specification.filter.MenuCategoryRequestFilter;
import org.commerce.sale_management.vo.menuCategory.MenuCategoryRequest;
import org.module.publish_service.service.BaseInternalService;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/2/2026 5:56 AM
 */
public interface MenuCategoryService extends BaseInternalService<MenuCategory, Long, MenuCategoryRequestFilter> {
    MenuCategory save(MenuCategoryRequest data);

    MenuCategory update(MenuCategoryRequest data);

    String updateStatus(Long id, boolean isActive);

    MenuCategory getMenuCategoryOrThrow(Long id);


}
