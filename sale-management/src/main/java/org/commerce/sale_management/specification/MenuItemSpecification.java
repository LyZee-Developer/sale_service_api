package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.MenuItem;
import org.commerce.sale_management.specification.filter.MenuItemRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

import java.math.BigDecimal;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 5:49 AM
 */
public class MenuItemSpecification extends BaseInternalSpecification<MenuItem, MenuItemRequestFilter> {
    public MenuItemSpecification(MenuItemRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<MenuItem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<MenuItem> specificationUtil = new SpecificationUtil<>(root, query, cb);
        String search = queryFilters.getSearch().trim();
        if (!search.isEmpty()) {
            predicates.add(
                cb.and(
                    cb.or(
                        specificationUtil.matchText("name", search),
                        specificationUtil.matchText("nameEn", search),
                        specificationUtil.search("name", search),
                        specificationUtil.search("nameEn", search),
                        specificationUtil.search("description", search),
                        specificationUtil.search("descriptionEn", search)
                    )
                )
            );
        }
    }
}
