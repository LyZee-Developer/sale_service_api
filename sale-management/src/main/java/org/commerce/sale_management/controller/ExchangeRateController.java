package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.ApiUrlConstant;
import org.commerce.sale_management.dto.ExchangeRateDTO;
import org.commerce.sale_management.model.ExchangeRate;
import org.commerce.sale_management.service.ExchangeRateService;
import org.commerce.sale_management.specification.filter.ExchangeRateRequestFilter;
import org.commerce.sale_management.vo.exchangeRate.ExchangeRateRequest;
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
 * @date : 11/5/2026 10:13 PM
 */

@RestController
@RequestMapping(ApiUrlConstant.EXCHANGE_RATE)
public class ExchangeRateController {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute ExchangeRateRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(exchangeRateService.getAllOrView(filter, pageable, ExchangeRateDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> index(@PathVariable Long id) {
        return ResponseEntity.ok(apiResponse.success(exchangeRateService.getOneOrView(id, ExchangeRateDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody ExchangeRateRequest request) {
        ExchangeRate emp = exchangeRateService.create(request);
        return ResponseEntity.ok(apiResponse.success(emp));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody ExchangeRateRequest request) {
        ExchangeRate emp = exchangeRateService.update(request);
        return ResponseEntity.ok(apiResponse.success(emp));
    }

    @GetMapping("/change-status")
    public ResponseEntity<Object> changeStatus(@RequestParam @Positive Long id, @RequestParam(required = false) @DefaultValue("false") Boolean isActivate) {
        return ResponseEntity.ok(apiResponse.success(exchangeRateService.changeStatus(id, isActivate)));
    }
}
