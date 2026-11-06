package org.commerce.sale_management.repository;

import org.springframework.data.repository.query.Param;
import org.commerce.sale_management.model.Order;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:24 PM
 */
public interface OrderRepository extends BaseCombineRepositorySpecification<Order, Long>, JpaSpecificationExecutor<Order> {
    boolean existsByOrderNumber(String code);

    @Query(value = """
        SELECT o.*
            FROM orders o
            WHERE o.table_id = :id
              AND o.order_date::DATE = CAST(:date_info AS DATE)
              AND o.status = 'START'
              AND o.is_activate = true
        """, nativeQuery = true)
    List<Order> isTableOrdered(@Param("id") Long id, @Param("date_info") LocalDateTime date_info);

    @Query(value = """
        SELECT o.*
        FROM orders o
        WHERE o.order_date::DATE = CAST(:date_info AS DATE)
          AND o.status = 'START'
          AND o.is_activate = true
        """, nativeQuery = true)
    List<Order> tableIsBusy(
        @Param("date_info") LocalDateTime dateInfo
    );
}
