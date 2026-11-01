package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.RouteConst;
import org.commerce.sale_management.dto.CustomerDTO;
import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.service.CustomerService;
import org.commerce.sale_management.specification.filter.CustomerRequestFilter;
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
 * @date : 11/1/2026 7:25 PM
 */
@RestController
@RequestMapping(RouteConst.CUSTOMER)
public class CustomerController {

    @Autowired
    private ApiResponse apiResponse;

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute CustomerRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(customerService.getAllOrView(filter, pageable, CustomerDTO.class)));
    }

    @GetMapping("/me/{id}")
    public ResponseEntity<Object> index(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(customerService.getOneOrView(id, CustomerDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CustomerRequest req) {
        Customer customer = customerService.create(req);
        return ResponseEntity.ok(apiResponse.success(new CustomerDTO(customer)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody CustomerRequest req) {
        Customer customer = customerService.update(req);
        return ResponseEntity.ok(apiResponse.success(new CustomerDTO(customer)));
    }

    @GetMapping("/status")
    public ResponseEntity<Object> disabled(@RequestParam Long id, @RequestParam(required = false) @DefaultValue("false") boolean isEnabled) {
        String result = customerService.changeStatus(id, isEnabled);
        return ResponseEntity.ok(apiResponse.success(result));
    }
}
