package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.RouteConst;
import org.commerce.sale_management.dto.UserAccountDTO;
import org.commerce.sale_management.model.User;
import org.commerce.sale_management.service.UserAccountService;
import org.commerce.sale_management.specification.filter.UserAccountRequestFilter;
import org.commerce.sale_management.vo.userAccount.UserAccount;
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
 * @date : 10/31/2026 10:09 PM
 */
@RestController
@RequestMapping(RouteConst.USER_ACCOUNT)
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private ApiResponse apiResponse;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute UserAccountRequestFilter filter, @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(userAccountService.getAllOrView(filter, pageable, UserAccountDTO.class)));
    }

    @GetMapping("/me/{id}")
    public ResponseEntity<Object> getOne(@PathVariable @Positive Long id) {
        User user = userAccountService.getOneOrThrow(id);
        return ResponseEntity.ok(apiResponse.success(new UserAccountDTO(user)));
    }

    @PostMapping
    public ResponseEntity<Object> save(@Valid @RequestBody UserAccount.create req) {
        User user = userAccountService.save(req);
        return ResponseEntity.ok(apiResponse.success(new UserAccountDTO(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserAccount.login req) {
        String result = userAccountService.login(req);
        return ResponseEntity.ok(apiResponse.success(result));
    }

    @GetMapping("/allow-reset-pw")
    public ResponseEntity<Object> allowResetPw(@RequestParam String username) {
        String result = userAccountService.allowResetPw(username);
        return ResponseEntity.ok(apiResponse.success(result));
    }

    @PostMapping("/reset-pw")
    public ResponseEntity<Object> resetPassword(@Valid @RequestBody UserAccount.reset request) {
        String result = userAccountService.resetPassword(request);
        return ResponseEntity.ok(apiResponse.success(result));
    }

    @GetMapping("/disabled")
    public ResponseEntity<Object> disabledAccount(@RequestParam String username, @RequestParam(required = false) @DefaultValue(value = "false") boolean isActivate) {
        String result = userAccountService.updateActivateUser(username, isActivate);
        return ResponseEntity.ok(apiResponse.success(result));
    }

}
