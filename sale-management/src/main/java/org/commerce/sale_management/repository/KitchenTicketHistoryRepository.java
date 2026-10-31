package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.KitchenTicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/27/2026 7:34 PM
 */
public interface KitchenTicketHistoryRepository extends JpaRepository<KitchenTicketHistory, Long>, JpaSpecificationExecutor<KitchenTicketHistory> {
}
