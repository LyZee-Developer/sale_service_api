package org.commerce.sale_management.module.user_service.specification;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.model.User_;
import org.commerce.sale_management.module.user_service.specification.filter.UserRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;

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
    protected void addPredicates(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //search
        if(!StringUtil.isNullOrEmpty(queryFilters.getSearch())){
            String search = queryFilters.getSearch().trim();
            predicates.add(cb.like(cb.lower(root.get(User_.USERNAME)), "%"+ search.toLowerCase() +"%"));
        }
    }
}
