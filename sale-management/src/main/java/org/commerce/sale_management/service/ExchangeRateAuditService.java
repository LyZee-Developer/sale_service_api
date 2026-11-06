package org.commerce.sale_management.service;

import org.commerce.sale_management.model.ExchangeRate;
import org.commerce.sale_management.vo.exchangeRate.ExchangeRateRequest;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 10:03 PM
 */
public interface ExchangeRateAuditService {
    void saveAudit(ExchangeRate request);
}
