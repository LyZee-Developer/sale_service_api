package org.commerce.sale_management.module.user_service.service;

import org.commerce.sale_management.module.user_service.dto.UserDTO;
import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.request.user.UserRequest;
import org.commerce.sale_management.module.user_service.specification.filter.UserRequestFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:16 PM
 */
public interface UserService {
    List<UserDTO.view> index();
    Page<User> allData(UserRequestFilter filter, Pageable pageable);
    Page<User> pageUser(Pageable pageable);
    String save(UserRequest.save req);
    String update(UserRequest.update req);
    String disabled(Long id);
}
