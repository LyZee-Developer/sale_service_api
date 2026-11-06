package org.commerce.sale_management.service;

import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.model.Order;
import org.commerce.sale_management.model.OrderItem;
import org.commerce.sale_management.specification.filter.OrderRequestFilter;
import org.commerce.sale_management.vo.order.OrderRequest;
import org.module.publish_service.service.BaseInternalService;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 10:40 PM
 */
public interface OrderService extends BaseInternalService<Order, Long, OrderRequestFilter> {
    Order order(OrderRequest request);

    Order update(OrderRequest request);
    List<DinningTableDTO> getAvailableTable();
    List<OrderItem> saveOrderItem(Order order, List<OrderRequest.OrderItemRequest> items);
}
