package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.MenuCategory;
import org.commerce.sale_management.specification.filter.MenuCategoryRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/2/2026 5:52 AM
 */
public class MenuCategorySpecification extends BaseInternalSpecification<MenuCategory, MenuCategoryRequestFilter> {
    public MenuCategorySpecification(MenuCategoryRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<MenuCategory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<MenuCategory> spec = new SpecificationUtil<>(root, query, cb);
        String search = queryFilters.getSearch();
        if (!search.isEmpty()) {
            predicates.add(
                cb.and(
                    cb.or(
                        spec.matchText("name", search),
                        spec.matchText("nameEn", search),
                        spec.search("description", search),
                        spec.search("descriptionEn", search)
                    )
                )
            );
        }
    }
}
