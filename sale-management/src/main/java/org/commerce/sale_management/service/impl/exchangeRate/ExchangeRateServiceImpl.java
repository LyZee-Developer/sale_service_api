package org.commerce.sale_management.service.impl.exchangeRate;

import org.commerce.sale_management.constant.ExchangeRateConstant;
import org.commerce.sale_management.model.Currency;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.model.ExchangeRate;
import org.commerce.sale_management.repository.ExchangeRateRepository;
import org.commerce.sale_management.service.CurrencyService;
import org.commerce.sale_management.service.EmployeeService;
import org.commerce.sale_management.service.ExchangeRateAuditService;
import org.commerce.sale_management.service.ExchangeRateService;
import org.commerce.sale_management.specification.ExchangeRateSpecification;
import org.commerce.sale_management.specification.filter.ExchangeRateRequestFilter;
import org.commerce.sale_management.vo.exchangeRate.ExchangeRateRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 9:38 PM
 */
@Service
public class ExchangeRateServiceImpl extends BaseInternalServiceImpl<ExchangeRate, Long, ExchangeRateRepository, ExchangeRateRequestFilter, ExchangeRateSpecification> implements ExchangeRateService {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExchangeRateAuditService exchangeRateAuditService;

    @Override
    public ExchangeRate create(ExchangeRateRequest request) {
        ExchangeRate save = save(mapData(request));
        //save history
        exchangeRateAuditService.saveAudit(save);
        return save;
    }

    @Override
    public ExchangeRate update(ExchangeRateRequest request) {
        this.getOneOrThrow(request.getId());
        ExchangeRate update = update(mapData(request));

        //save history
        exchangeRateAuditService.saveAudit(update);
        return update;
    }

    private ExchangeRate mapData(ExchangeRateRequest request) {
        ExchangeRate exchangeRate = Optional.ofNullable(request.getId()).map(this::getOneOrThrow).orElseGet(ExchangeRate::new);
        if (request.getId() == null) {
            validateCurrency(request);
        }
        exchangeRate.setCurrencyFrom(getCurrency(request).get(ExchangeRateConstant.FROM));
        exchangeRate.setCurrencyTo(getCurrency(request).get(ExchangeRateConstant.TO));
        exchangeRate.setDate(LocalDate.now());
        exchangeRate.setAmountTo(request.getAmountTo());
        exchangeRate.setAmountFrom(request.getAmountFrom());
        exchangeRate.setCalcAmount(exchangeRate.calculateAmount());

        Employee emp = validateOrGetEmployee(request);
        exchangeRate.setCreatedByEmployee(emp);
        return exchangeRate;
    }

    @Override
    public String changeStatus(Long id, boolean isActivate) {
        ExchangeRate exchangeRate = this.getOneOrThrow(id);
        exchangeRate.setIsActivate(isActivate);
        update(exchangeRate);
        return "%s exchange rate success.".formatted(isActivate ? "Enabled" : "Disabled");
    }

    private void validateCurrency(ExchangeRateRequest request) {
        boolean isExisted = repository.existsByCurrencyFromCodeAndCurrencyToCodeAndIsActivateTrue(request.getCurrencyFrom(), request.getCurrencyTo());
        String fromSymbol = getCurrency(request).get(ExchangeRateConstant.FROM).getCode();
        String toSymbol = getCurrency(request).get(ExchangeRateConstant.TO).getCode();
        if (isExisted) {
            throw new ApiResponseHandler("Exchange rate from %s to %s already existed.".formatted(fromSymbol, toSymbol));
        }
    }

    private Employee validateOrGetEmployee(ExchangeRateRequest request) {
        try {
            return employeeService.getOneOrThrow(request.getEmployeeId());
        } catch (Exception ex) {
            throw new ApiResponseHandler("Employee not found.");
        }
    }

    private Map<String, Currency> getCurrency(ExchangeRateRequest request) {
        Currency from = currencyService.getCurrencyByCode(request.getCurrencyFrom());
        Currency to = currencyService.getCurrencyByCode(request.getCurrencyTo());
        return Map.of(
            ExchangeRateConstant.FROM, from,
            ExchangeRateConstant.TO, to
        );
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
