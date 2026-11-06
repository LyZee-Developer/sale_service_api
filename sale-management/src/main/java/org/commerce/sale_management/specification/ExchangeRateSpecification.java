package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.ExchangeRate;
import org.commerce.sale_management.specification.filter.ExchangeRateRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 8:42 PM
 */
public class ExchangeRateSpecification extends BaseInternalSpecification<ExchangeRate, ExchangeRateRequestFilter> {
    public ExchangeRateSpecification(ExchangeRateRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<ExchangeRate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<ExchangeRate> spec = new SpecificationUtil<>(root, query, cb);
        String search = queryFilters.getSearch();
        if (!search.isEmpty()) {
            predicates.add(
                cb.and(
                    cb.or(
                        spec.matchText("currency_to_code", search),
                        spec.matchText("currency_from_code", search)
                    )
                )
            );
        }
    }
}
