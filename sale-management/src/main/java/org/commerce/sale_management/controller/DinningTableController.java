package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.RouteConst;
import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.service.DinningTableService;
import org.commerce.sale_management.specification.filter.DinningTableRequestFilter;
import org.commerce.sale_management.vo.dinning.DinningTableRequest;
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
 * @date : 11/1/2026 1:31 PM
 */
@RestController
@RequestMapping(RouteConst.DINNING_TABLE)
public class DinningTableController {
    @Autowired
    private DinningTableService dinningTableService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute DinningTableRequestFilter filter,
                                        @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(dinningTableService.getAllOrView(filter, pageable, DinningTableDTO.class)));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Object> getOne(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(dinningTableService.getOneOrView(id, DinningTableDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody DinningTableRequest.create req) {
        DinningTable table = dinningTableService.save(req);
        return ResponseEntity.ok(apiResponse.success(new DinningTableDTO(table)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody DinningTableRequest.update req) {
        DinningTable table = dinningTableService.update(req);
        return ResponseEntity.ok(apiResponse.success(new DinningTableDTO(table)));
    }

    @GetMapping("/disabled")
    public ResponseEntity<Object> disabled(@RequestParam Long id, @RequestParam(required = false) @DefaultValue("false") boolean activate) {
        String result = dinningTableService.updateStatus(id, activate);
        return ResponseEntity.ok(apiResponse.success(result));
    }
}
