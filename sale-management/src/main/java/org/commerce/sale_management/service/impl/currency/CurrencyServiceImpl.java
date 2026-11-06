package org.commerce.sale_management.service.impl.currency;

import org.commerce.sale_management.model.Currency;
import org.commerce.sale_management.repository.CurrencyRepository;
import org.commerce.sale_management.service.CurrencyService;
import org.commerce.sale_management.specification.CurrencySpecification;
import org.commerce.sale_management.specification.filter.CurrencyRequestFilter;
import org.commerce.sale_management.vo.currency.CurrencyRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 6:57 PM
 */
@Service
public class CurrencyServiceImpl extends BaseInternalServiceImpl<Currency, Long, CurrencyRepository, CurrencyRequestFilter, CurrencySpecification> implements CurrencyService {
    @Override
    public Currency create(CurrencyRequest req) {
        return save(mapInfoCurrency(req));
    }

    @Override
    public Currency update(CurrencyRequest req) {
        this.findOneOrThrow(req.getId());
        return update(mapInfoCurrency(req));
    }

    private Currency mapInfoCurrency(CurrencyRequest req) {
        if (req.getId() == null) {
            checkExistCode(req.getCode());
        }

        Currency cur = Optional.ofNullable(req.getId()).map(this::findOneOrThrow).orElseGet(Currency::new);
        boolean isBaseCurrency = isEmptyData();
        if (req.getId() == null) {
            cur.setCode(req.getCode());
        }
        cur.setName(req.getName());
        cur.setNameEn(req.getNameEn());
        cur.setSymbol(req.getSymbol());
        cur.setDecimalPlaces(req.getDecimalPlaces());
        cur.setIsBase(isBaseCurrency);
        return cur;
    }

    public Currency getCurrencyByCode(String code) {
        return repository.findByCodeAndIsActivateTrue(code).orElseThrow(() -> new ApiResponseHandler("Currency not found."));
    }

    @Override
    public String changeBaseCurrencyTo(Long id) {
        List<Currency> all = repository.findAll();
        if (all.isEmpty()) {
            throw new ApiResponseHandler("System did not have currency yet.");
        }
        Currency baseCur = this.findOneOrThrow(id);
        all.stream().filter(Currency::getIsActivate).forEach(cur -> {
            boolean isBase = Objects.equals(cur.getId(), id);
            cur.setIsBase(isBase);
        });
        update(all);
        return "Update base currency to [ %s ] successfully.".formatted(baseCur.getSymbol());
    }

    private void checkExistCode(String code) {
        boolean isExist = repository.existsByCodeAndIsActivateTrue(code);
        if (isExist) {
            throw new ApiResponseHandler("code already existed.");
        }
    }

    private boolean isEmptyData() {
        return repository.findAll().isEmpty();
    }

    @Override
    public String changeStatus(Long id, Boolean isActive) {
        Currency currency = this.findOneOrThrow(id);
        currency.setIsActivate(isActive);
        update(currency);
        return "%s currency success.".formatted(isActive ? "Enabled" : "Disabled");
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
