package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Order;
import org.commerce.sale_management.specification.filter.OrderRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 10:38 PM
 */
public class OrderSpecification extends BaseInternalSpecification<Order, OrderRequestFilter> {
    public OrderSpecification(OrderRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<Order> spec = new SpecificationUtil<>(root, query, cb);

        String search = queryFilters.getSearch().trim();
        if (!search.isEmpty()) {
            predicates.add(cb.and(cb.or(spec.matchText("code", search), spec.search("code", search))));
        }
    }
}
