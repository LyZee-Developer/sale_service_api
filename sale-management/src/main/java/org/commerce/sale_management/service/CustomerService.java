package org.commerce.sale_management.service;

import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.model.Order;
import org.commerce.sale_management.specification.filter.CustomerRequestFilter;
import org.commerce.sale_management.vo.customer.CustomerRequest;
import org.commerce.sale_management.vo.order.OrderRequest;
import org.module.publish_service.service.BaseInternalService;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 7:38 PM
 */
public interface CustomerService extends BaseInternalService<Customer, Long, CustomerRequestFilter> {
    Customer create(CustomerRequest request);

    Customer update(CustomerRequest request);

    String changeStatus(Long id, boolean isEnabled);

    Optional<Customer> getCustomerCode(String code);


}
