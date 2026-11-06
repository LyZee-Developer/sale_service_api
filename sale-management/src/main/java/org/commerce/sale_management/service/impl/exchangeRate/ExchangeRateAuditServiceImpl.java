package org.commerce.sale_management.service.impl.exchangeRate;

import org.commerce.sale_management.model.ExchangeRate;
import org.commerce.sale_management.model.ExchangeRateAudit;
import org.commerce.sale_management.repository.ExchangeRateAuditRepository;
import org.commerce.sale_management.service.ExchangeRateAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 10:04 PM
 */
@Service
public class ExchangeRateAuditServiceImpl implements ExchangeRateAuditService {
    @Autowired
    private ExchangeRateAuditRepository exchangeRateAuditRepository;

    @Override
    public void saveAudit(ExchangeRate request) {
        ExchangeRateAudit audit = new ExchangeRateAudit(request);
        exchangeRateAuditRepository.save(audit);
    }
}
