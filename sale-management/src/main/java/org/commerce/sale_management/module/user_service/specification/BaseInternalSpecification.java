package org.commerce.sale_management.module.user_service.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.module.publish_service.model.BaseEntity;
import org.module.publish_service.request.BaseInternalRequestFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 3:26 PM
 */
@Getter
@Setter
public class BaseInternalSpecification<ENT extends BaseEntity, FT extends BaseInternalRequestFilter> implements Specification<ENT> {
    protected FT queryFilters;
    protected List<Predicate> predicates = new ArrayList<>();

    public BaseInternalSpecification(FT filter) {
        this.queryFilters = filter;
    }

    @Override
    public @Nullable Predicate toPredicate(Root<ENT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        if (queryFilters.getIsActivate()) {
            predicates.add(cb.equal(root.get("isActivate"), queryFilters.getIsActivate()));
        } else {
            predicates.add(cb.equal(root.get("isActivate"), Boolean.TRUE));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
