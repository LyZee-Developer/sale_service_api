package org.commerce.sale_management.module.user_service.specification;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.specification.filter.UserRequestFilter;
import org.jspecify.annotations.Nullable;
import org.module.publish_service.request.BaseInternalRequestFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 3:12 PM
 */
public class UserSpecification extends BaseInternalSpecification<User, UserRequestFilter> {

    public UserSpecification(UserRequestFilter filter) {
        super(filter);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //search
        if(!StringUtil.isNullOrEmpty(queryFilters.getSearch())){
            String search = queryFilters.getSearch().trim();
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("password")), "%"+ search.toLowerCase() +"%"),
                    cb.like(cb.lower(root.get("username")), "%"+ search.toLowerCase() +"%")
            ));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
