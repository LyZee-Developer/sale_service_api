package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.ApiUrlConstant;
import org.commerce.sale_management.dto.CurrencyDTO;
import org.commerce.sale_management.dto.CustomerDTO;
import org.commerce.sale_management.model.Currency;
import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.service.CurrencyService;
import org.commerce.sale_management.service.CustomerService;
import org.commerce.sale_management.specification.filter.CurrencyRequestFilter;
import org.commerce.sale_management.specification.filter.CustomerRequestFilter;
import org.commerce.sale_management.vo.currency.CurrencyRequest;
import org.commerce.sale_management.vo.customer.CustomerRequest;
import org.module.publish_service.model.BaseEntity_;
import org.module.publish_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 7:31 PM
 */
@RestController
@RequestMapping(ApiUrlConstant.CURRENCY)
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute CurrencyRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(currencyService.getAllOrView(filter, pageable, CurrencyDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> index(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(currencyService.getOneOrView(id, CurrencyDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CurrencyRequest req) {
        Currency currency = currencyService.create(req);
        return ResponseEntity.ok(apiResponse.success(new CurrencyDTO(currency)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody CurrencyRequest req) {
        Currency currency = currencyService.update(req);
        return ResponseEntity.ok(apiResponse.success(new CurrencyDTO(currency)));
    }

    @GetMapping("/status")
    public ResponseEntity<Object> disabled(@RequestParam Long id, @RequestParam(required = false) @DefaultValue("false") boolean isEnabled) {
        String result = currencyService.changeStatus(id, isEnabled);
        return ResponseEntity.ok(apiResponse.success(result));
    }

    @GetMapping("/change-base-currency/{id}")
    public ResponseEntity<Object> changeBaseCurrencyTo(@PathVariable Long id) {
        String result = currencyService.changeBaseCurrencyTo(id);
        return ResponseEntity.ok(apiResponse.success(result));
    }
}
