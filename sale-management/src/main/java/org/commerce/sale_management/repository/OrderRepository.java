package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.Order;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:24 PM
 */
public interface OrderRepository extends BaseCombineRepositorySpecification<Order, Long>, JpaSpecificationExecutor<Order> {
}
