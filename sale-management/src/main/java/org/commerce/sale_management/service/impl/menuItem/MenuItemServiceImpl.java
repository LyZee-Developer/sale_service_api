package org.commerce.sale_management.service.impl.menuItem;

import org.commerce.sale_management.model.MenuCategory;
import org.commerce.sale_management.model.MenuItem;
import org.commerce.sale_management.repository.MenuItemRepository;
import org.commerce.sale_management.service.MenuCategoryService;
import org.commerce.sale_management.service.MenuItemService;
import org.commerce.sale_management.specification.MenuItemSpecification;
import org.commerce.sale_management.specification.filter.MenuItemRequestFilter;
import org.commerce.sale_management.vo.menuItem.MenuItemRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 5:57 AM
 */
@Service
public class MenuItemServiceImpl extends BaseInternalServiceImpl<MenuItem, Long, MenuItemRepository, MenuItemRequestFilter, MenuItemSpecification> implements MenuItemService {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @Override
    public MenuItem save(MenuItemRequest data) {
        return save(mpaInfo(data));
    }

    private MenuItem mpaInfo(MenuItemRequest req) {
        Assert.notNull(req.getCategoryId(), "category id not found!");
        MenuCategory menuCategory = menuCategoryService.getMenuCategoryOrThrow(req.getCategoryId());
        MenuItem item = getMenuItemOrCreate(req.getId());

        item.setName(req.getName());
        item.setNameEn(req.getNameEn());
        item.setIsAvailable(req.getIsAvailable());
        item.setPrice(req.getPrice());
        item.setDiscount(req.getDiscount());
        item.setDescription(req.getDescription());
        item.setDescriptionEn(req.getDescriptionEn());
        item.setCategory(menuCategory);
        return item;
    }

    private MenuItem getMenuItemOrCreate(Long id) {
        return Optional.ofNullable(id).map(this::getOneOrThrow).orElseGet(MenuItem::new);
    }

    @Override
    public MenuItem update(MenuItemRequest data) {
        Assert.notNull(data.getId(), "id not found!");
        this.getOneOrThrow(data.getId());
        return update(mpaInfo(data));
    }

    @Override
    public String updateStatus(Long id, boolean isActive) {
        MenuItem menuItem = getOneOrThrow(id);
        menuItem.setIsActivate(isActive);
        update(menuItem);
        return "%s customer success.".formatted(isActive ? "Enabled" : "Disabled");
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
