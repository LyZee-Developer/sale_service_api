package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.MenuItem;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:02 PM
 */
public interface MenuItemRepository extends BaseCombineRepositorySpecification<MenuItem, Long> {
    Optional<List<MenuItem>> findAllByCategoryIdAndIsActivateTrue(Long categoryId);
}
