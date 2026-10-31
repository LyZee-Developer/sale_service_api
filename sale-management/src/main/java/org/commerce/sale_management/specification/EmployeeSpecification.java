package org.commerce.sale_management.specification;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.specification.filter.EmployeeRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;

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
        //search
        if (!StringUtil.isNullOrEmpty(queryFilters.getSearch())) {
            String search = queryFilters.getSearch().trim();
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("username")), "%" + search.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("password")), "%" + search.toLowerCase() + "%")
            ));
        }
    }
}
