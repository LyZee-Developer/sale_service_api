package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.KitchenTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:48 PM
 */
public interface KitchenTicketRepository extends JpaRepository<KitchenTicket, Long>, JpaSpecificationExecutor<KitchenTicket> {
}
