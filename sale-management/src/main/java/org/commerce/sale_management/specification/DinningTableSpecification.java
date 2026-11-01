package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.DinningTable_;
import org.commerce.sale_management.specification.filter.DinningTableRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;
import org.module.publish_service.util.StringUtility;
import org.springframework.util.StringUtils;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:33 PM
 */
public class DinningTableSpecification extends BaseInternalSpecification<DinningTable, DinningTableRequestFilter> {
    public DinningTableSpecification(DinningTableRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<DinningTable> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<DinningTable> specification = new SpecificationUtil<>(root, query, cb);


        String search = queryFilters.getSearch().trim();
        if (StringUtils.hasLength(search)) {
            boolean isNumber = StringUtility.isNumber(search);
            if (isNumber) {
                predicates.add(
                    cb.or(
                        specification.matchNumber(DinningTable_.CAPACITY, search),
                        specification.search(DinningTable_.TABLE_NAME, search)
                    )
                );
            } else {
                predicates.add(
                    cb.or(
                        specification.matchText(DinningTable_.TABLE_NAME, search),
                        specification.search(DinningTable_.DESCRIPTION, search),
                        specification.search(DinningTable_.TABLE_NAME, search)
                    )
                );
            }

        }
    }
}
