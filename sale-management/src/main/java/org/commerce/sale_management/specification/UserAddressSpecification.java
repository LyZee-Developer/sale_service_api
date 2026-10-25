package org.commerce.sale_management.specification;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.UserAddress;
import org.commerce.sale_management.specification.filter.UserAddressRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 3:12 PM
 */
public class UserAddressSpecification extends BaseInternalSpecification<UserAddress, UserAddressRequestFilter> {

    public UserAddressSpecification(UserAddressRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<UserAddress> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        //search
        if(!StringUtil.isNullOrEmpty(queryFilters.getSearch())){
            String search = queryFilters.getSearch().trim();
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get("username")), "%"+ search.toLowerCase() +"%"),
                    cb.like(cb.lower(root.get("password")), "%"+ search.toLowerCase() +"%")
            ));
        }
    }
}
