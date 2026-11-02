package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.RouteConst;
import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.dto.MenuCategoryDTO;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.MenuCategory;
import org.commerce.sale_management.service.DinningTableService;
import org.commerce.sale_management.service.MenuCategoryService;
import org.commerce.sale_management.specification.filter.DinningTableRequestFilter;
import org.commerce.sale_management.specification.filter.MenuCategoryRequestFilter;
import org.commerce.sale_management.vo.dinning.DinningTableRequest;
import org.commerce.sale_management.vo.menuCategory.MenuCategoryRequest;
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
 * @date : 11/2/2026 6:02 AM
 */
@RestController
@RequestMapping(RouteConst.MENU_CATEGORY)
public class MenuCategoryController {
    @Autowired
    private MenuCategoryService menuCategoryService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute MenuCategoryRequestFilter filter,
                                        @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(menuCategoryService.getAllOrView(filter, pageable, MenuCategoryDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(menuCategoryService.getOneOrView(id, MenuCategoryDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody MenuCategoryRequest req) {
        MenuCategory menuCategory = menuCategoryService.save(req);
        return ResponseEntity.ok(apiResponse.success(new MenuCategoryDTO(menuCategory)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody MenuCategoryRequest req) {
        MenuCategory menuCategory = menuCategoryService.update(req);
        return ResponseEntity.ok(apiResponse.success(new MenuCategoryDTO(menuCategory)));
    }

    @GetMapping("/disabled")
    public ResponseEntity<Object> disabled(@RequestParam Long id, @RequestParam(required = false) @DefaultValue("false") boolean activate) {
        String result = menuCategoryService.updateStatus(id, activate);
        return ResponseEntity.ok(apiResponse.success(result));
    }
}
