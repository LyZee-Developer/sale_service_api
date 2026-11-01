package org.commerce.sale_management.specification;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.model.Employee_;
import org.commerce.sale_management.specification.filter.EmployeeRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 11:06 PM
 */
public class EmployeeSpecification extends BaseInternalSpecification<Employee, EmployeeRequestFilter> {
    public EmployeeSpecification(EmployeeRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<Employee> spec = new SpecificationUtil<>(root, query, cb);

        if (!StringUtil.isNullOrEmpty(queryFilters.getSearch())) {
            String search = queryFilters.getSearch();
            predicates.add(
                cb.or(
                    spec.matchText(Employee_.CODE, search),
                    spec.matchText(Employee_.PHONE, search),
                    spec.matchText(Employee_.PHONE1, search),
                    spec.search(Employee_.EMAIL, search),
                    spec.concatSearch(Employee_.FIRST_NAME_EN, Employee_.LAST_NAME_EN, search),
                    spec.concatSearch(Employee_.FIRST_NAME, Employee_.FIRST_NAME, search)
                )
            );
        }
    }
}
