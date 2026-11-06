package org.commerce.sale_management.service;

import org.commerce.sale_management.model.Currency;
import org.commerce.sale_management.specification.filter.CurrencyRequestFilter;
import org.commerce.sale_management.vo.currency.CurrencyRequest;
import org.module.publish_service.service.BaseInternalService;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 6:53 PM
 */
public interface CurrencyService extends BaseInternalService<Currency, Long, CurrencyRequestFilter> {
    Currency create(CurrencyRequest req);

    Currency update(CurrencyRequest req);

    String changeStatus(Long id, Boolean isActive);

    String changeBaseCurrencyTo(Long id);

    Currency getCurrencyByCode(String code);
}
