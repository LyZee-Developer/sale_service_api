package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:36 PM
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
