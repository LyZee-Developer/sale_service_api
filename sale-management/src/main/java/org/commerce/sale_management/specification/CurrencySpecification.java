package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Currency;
import org.commerce.sale_management.specification.filter.CurrencyRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 6:48 PM
 */
public class CurrencySpecification extends BaseInternalSpecification<Currency, CurrencyRequestFilter> {
    public CurrencySpecification(CurrencyRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<Currency> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<Currency> spec = new SpecificationUtil<>(root, query, cb);
        String search = queryFilters.getSearch().trim();
        if (!search.isEmpty()) {
            predicates.add(
                cb.or(
                    spec.matchText("code", search),
                    spec.matchText("nameEn", search),
                    spec.matchText("name", search),
                    spec.search("name", search),
                    spec.search("nameEn", search),
                    spec.search("code", search)
                )
            );
        }
        super.addPredicates(root, query, cb);
    }
}
