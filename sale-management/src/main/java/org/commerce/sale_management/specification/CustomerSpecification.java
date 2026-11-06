package org.commerce.sale_management.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.specification.filter.CustomerRequestFilter;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.module.publish_service.util.SpecificationUtil;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 7:31 PM
 */
public class CustomerSpecification extends BaseInternalSpecification<Customer, CustomerRequestFilter> {
    public CustomerSpecification(CustomerRequestFilter filter) {
        super(filter);
    }

    @Override
    protected void addPredicates(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        SpecificationUtil<Customer> specification = new SpecificationUtil<>(root, query, cb);
        String searchText = Optional.ofNullable(queryFilters.getSearch()).map(String::trim).orElse("");
        if (!ObjectUtils.isEmpty(searchText)) {
            predicates.add(
                cb.or(
                    specification.matchText("fullName", searchText),
                    specification.matchText("fullNameEn", searchText),
                    specification.matchText("code", searchText),
                    specification.matchText("phone", searchText),
                    specification.search("email", searchText)
                )
            );
        }

        String customerType = Optional.ofNullable(queryFilters.getCustomerType()).map(String::trim).orElse("");
        if (!ObjectUtils.isEmpty(customerType)) {
            predicates.add(
                cb.or(
                    specification.matchText("type", customerType)
                )
            );
        }

    }
}
