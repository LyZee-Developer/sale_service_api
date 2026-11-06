package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.Floor;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 6:02 AM
 */
public interface FloorRepository extends BaseCombineRepositorySpecification<Floor, Long> {
    boolean existsByNameAndIsActivateTrue(String name);
}
