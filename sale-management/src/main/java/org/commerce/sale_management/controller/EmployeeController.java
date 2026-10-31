package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.RouteConst;
import org.commerce.sale_management.dto.EmployeeViewDTO;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.service.EmployeeService;
import org.commerce.sale_management.specification.filter.EmployeeRequestFilter;
import org.commerce.sale_management.vo.employee.EmployeeRequest;
import org.module.publish_service.model.BaseEntity_;
import org.module.publish_service.response.ApiResponse;
import org.module.publish_service.util.GenerateUtil;
import org.module.publish_service.util.SensitiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 10:53 PM
 */
@RestController
@RequestMapping(RouteConst.EMPLOYEE)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute EmployeeRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(employeeService.getAllOrView(filter, pageable, EmployeeViewDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody EmployeeRequest request) {
        EmployeeViewDTO emp = employeeService.save(request);
        return ResponseEntity.ok(apiResponse.success(emp));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody EmployeeRequest.update request) {
        Employee emp = employeeService.update(request.getId(), request);
        return ResponseEntity.ok(apiResponse.success(emp));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> update(@PathVariable @Positive Long id) {
        Employee emp = employeeService.getOne(id);
        return ResponseEntity.ok(apiResponse.success(emp));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(employeeService.delete(id)));
    }
}
