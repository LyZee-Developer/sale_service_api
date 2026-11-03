package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.ApiUrlConstant;
import org.commerce.sale_management.dto.MenuItemDTO;
import org.commerce.sale_management.model.MenuItem;
import org.commerce.sale_management.service.MenuItemService;
import org.commerce.sale_management.specification.filter.MenuItemRequestFilter;
import org.commerce.sale_management.vo.menuItem.MenuItemRequest;
import org.module.publish_service.model.BaseEntity_;
import org.module.publish_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 6:08 AM
 */
@RestController
@RequestMapping(ApiUrlConstant.MENU_ITEM)
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute MenuItemRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(menuItemService.getAllOrView(filter, pageable, MenuItemDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(menuItemService.getOneOrView(id, MenuItemDTO.class)));
    }

    @GetMapping("/category-items/{id}")
    public ResponseEntity<Object> CategoryItems(@PathVariable @Positive Long id) {
        List<MenuItemDTO> allItem = menuItemService.getItemByCategoryId(id);
        return ResponseEntity.ok(apiResponse.success(allItem));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody MenuItemRequest req) {
        MenuItem menuItem = menuItemService.save(req);
        return ResponseEntity.ok(apiResponse.success(new MenuItemDTO(menuItem)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody MenuItemRequest req) {
        MenuItem menuItem = menuItemService.update(req);
        return ResponseEntity.ok(apiResponse.success(new MenuItemDTO(menuItem)));
    }

    @GetMapping("/disabled")
    public ResponseEntity<Object> disabled(@RequestParam Long id, @RequestParam(required = false) @DefaultValue("false") boolean inactivate) {
        String result = menuItemService.updateStatus(id, inactivate);
        return ResponseEntity.ok(apiResponse.success(result));
    }

    @GetMapping("/change-item-available")
    public ResponseEntity<Object> create(@RequestParam Long id, @RequestParam boolean isAvailable) {
        String msg = menuItemService.changeItemStatusAvailable(id, isAvailable);
        return ResponseEntity.ok(apiResponse.success(msg));
    }
}
