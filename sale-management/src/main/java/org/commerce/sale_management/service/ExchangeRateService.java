package org.commerce.sale_management.service;

import org.commerce.sale_management.model.ExchangeRate;
import org.commerce.sale_management.specification.filter.ExchangeRateRequestFilter;
import org.commerce.sale_management.vo.exchangeRate.ExchangeRateRequest;
import org.module.publish_service.service.BaseInternalService;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 9:35 PM
 */
public interface ExchangeRateService extends BaseInternalService<ExchangeRate, Long, ExchangeRateRequestFilter> {
    ExchangeRate create(ExchangeRateRequest request);
    ExchangeRate update(ExchangeRateRequest request);
    String changeStatus(Long id, boolean isActivate);
}
