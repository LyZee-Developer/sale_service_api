package org.commerce.sale_management.repository;

import org.springframework.data.repository.query.Param;
import org.commerce.sale_management.model.DinningTable;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 6:02 AM
 */
public interface DinningTableRepository extends BaseCombineRepositorySpecification<DinningTable, Long> {
    Optional<DinningTable> findByTableNameAndIsActivateTrue(String tableName);

    @Query(
        value = """
            SELECT t.* from dinning_table t
            where t.id not in (:tableIds)
            and t.is_activate = true
            """
        , nativeQuery = true)
    List<DinningTable> getTableAvailable(@Param("tableIds") List<Long> tableIds);

    // used when no table is busy: "not in ()" with an empty list is invalid SQL
    List<DinningTable> findAllByIsActivateTrue();
}
