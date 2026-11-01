package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.model.Employee_;
import org.commerce.sale_management.model.User;
import org.commerce.sale_management.model.User_;
import org.commerce.sale_management.specification.filter.UserAccountRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/31/2026 10:24 PM
 */
public class UserAccountSpecification extends BaseInternalSpecification<User, UserAccountRequestFilter> {
    public UserAccountSpecification(UserAccountRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<User> spec = new SpecificationUtil<>(root, query, cb);

        Join<User, Employee> empJoin = root.join(User_.EMPLOYEE);

        String search = queryFilters.getSearch().trim();
        if (!search.isEmpty()) {
            predicates.add(
                cb.and(
                    cb.or(
                        spec.matchText(User_.USERNAME, search),
                        spec.search(User_.USERNAME, search),
                        cb.like(cb.lower(cb.concat(cb.concat(empJoin.get(Employee_.FIRST_NAME), " "), empJoin.get(Employee_.LAST_NAME))), "%" + search.toLowerCase() + "%"),
                        cb.like(cb.lower(cb.concat(cb.concat(empJoin.get(Employee_.FIRST_NAME_EN), " "), empJoin.get(Employee_.LAST_NAME_EN))), "%" + search.toLowerCase() + "%")
                    )
                )
            );
        }
    }
}
