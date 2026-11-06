package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Floor;
import org.commerce.sale_management.specification.filter.FloorRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 10:45 PM
 */
public class FloorSpecification extends BaseInternalSpecification<Floor, FloorRequestFilter> {
    public FloorSpecification(FloorRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<Floor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<Floor> spec = new SpecificationUtil<>(root, query, cb);
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
