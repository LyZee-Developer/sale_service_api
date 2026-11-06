package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.ApiUrlConstant;
import org.commerce.sale_management.dto.MenuItemDTO;
import org.commerce.sale_management.dto.OrderDTO;
import org.commerce.sale_management.service.MenuItemService;
import org.commerce.sale_management.service.OrderService;
import org.commerce.sale_management.specification.filter.MenuItemRequestFilter;
import org.commerce.sale_management.specification.filter.OrderRequestFilter;
import org.commerce.sale_management.vo.order.OrderRequest;
import org.module.publish_service.model.BaseEntity_;
import org.module.publish_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 10:40 PM
 */
@RestController
@RequestMapping(ApiUrlConstant.ORDER)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute OrderRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(orderService.getAllOrView(filter, pageable, OrderDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(orderService.getOneOrView(id, OrderDTO.class)));
    }

    @GetMapping("/available-table")
    public ResponseEntity<Object> availableTable() {
        return ResponseEntity.ok(apiResponse.success(orderService.getAvailableTable()));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(apiResponse.success(orderService.order(request)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(apiResponse.success(orderService.update(request)));
    }
}
