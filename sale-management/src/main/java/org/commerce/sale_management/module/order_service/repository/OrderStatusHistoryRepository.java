package org.commerce.sale_management.module.order_service.repository;

import org.commerce.sale_management.module.order_service.model.OrderStatusHistory;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.stereotype.Repository;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:00 PM
 */

@Repository
public interface OrderStatusHistoryRepository extends BaseCombineRepositorySpecification<OrderStatusHistory, Long> {
}
