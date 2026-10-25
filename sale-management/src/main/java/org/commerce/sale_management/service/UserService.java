package org.commerce.sale_management.service;

import org.commerce.sale_management.dto.UserDTO;
import org.commerce.sale_management.model.User;
import org.commerce.sale_management.user.UserRequest;
import org.commerce.sale_management.specification.filter.UserRequestFilter;
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
    User findById(Long id);

}
