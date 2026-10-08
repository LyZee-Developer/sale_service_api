package org.commerce.sale_management.module.user_service.controller;

import org.commerce.sale_management.module.user_service.constant.RouteConst;
import org.commerce.sale_management.module.user_service.request.user.UserRequest;
import org.commerce.sale_management.module.user_service.service.UserService;
import org.commerce.sale_management.module.user_service.specification.filter.UserRequestFilter;
import org.module.publish_service.model.BaseEntity_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/3/2026 6:25 PM
 */
@RestController
@RequestMapping(RouteConst.USER)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Object> index(){
        return ResponseEntity.ok(userService.index());
    }

    @GetMapping("/page")
    public ResponseEntity<Object> all(@PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(userService.pageUser(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> allData(@ModelAttribute UserRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(userService.allData(filter,pageable));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Validated @RequestBody UserRequest.save data){
        String result = userService.save(data);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Object> update(@Validated @RequestBody UserRequest.update data){
        String result = userService.update(data);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestParam Long id){
        String result = userService.disabled(id);
        return ResponseEntity.ok(result);
    }
}
