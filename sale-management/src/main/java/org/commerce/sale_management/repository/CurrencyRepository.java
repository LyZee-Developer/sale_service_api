package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.Currency;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/27/2026 5:41 AM
 */
public interface CurrencyRepository extends BaseCombineRepositorySpecification<Currency, Long> {
    boolean existsByCodeAndIsActivateTrue(String code);

    Optional<Currency> findByCodeAndIsActivateTrue(String code);
}
