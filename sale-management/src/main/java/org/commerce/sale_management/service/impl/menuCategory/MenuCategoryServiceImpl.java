package org.commerce.sale_management.service.impl.menuCategory;

import org.commerce.sale_management.model.MenuCategory;
import org.commerce.sale_management.repository.MenuCategoryRepository;
import org.commerce.sale_management.service.MenuCategoryService;
import org.commerce.sale_management.specification.MenuCategorySpecification;
import org.commerce.sale_management.specification.filter.MenuCategoryRequestFilter;
import org.commerce.sale_management.vo.menuCategory.MenuCategoryRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/2/2026 5:59 AM
 */
@Service
public class MenuCategoryServiceImpl extends BaseInternalServiceImpl<MenuCategory, Long, MenuCategoryRepository, MenuCategoryRequestFilter, MenuCategorySpecification> implements MenuCategoryService {
    @Override
    public MenuCategory save(MenuCategoryRequest data) {
        return save(mpaInfo(data));
    }

    @Override
    public MenuCategory update(MenuCategoryRequest data) {
        Assert.notNull(data.getId(), "Id is required.");
        getMenuCategoryOrThrow(data.getId());
        return update(mpaInfo(data));
    }

    private MenuCategory mpaInfo(MenuCategoryRequest req) {
        MenuCategory menuCategory = getMenuCategory(req.getId());
        menuCategory.setName(req.getName());
        menuCategory.setNameEn(req.getNameEn());
        menuCategory.setDescription(req.getDescription());
        menuCategory.setDescriptionEn(req.getDescriptionEn());
        return menuCategory;
    }

    private MenuCategory getMenuCategory(Long id) {
        return Optional.ofNullable(id).map(this::getOneOrThrow).orElseGet(MenuCategory::new);
    }

    @Override
    public MenuCategory getMenuCategoryOrThrow(Long id) {
        return this.getOneOrThrow(id);
    }


    @Override
    public String updateStatus(Long id, boolean isActive) {
        MenuCategory menuCategory = getMenuCategoryOrThrow(id);
        menuCategory.setIsActivate(isActive);
        update(menuCategory);
        return "%s customer success.".formatted(isActive ? "Enabled" : "Disabled");
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
