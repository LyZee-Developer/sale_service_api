package org.commerce.sale_management.module.user_service.service;

import org.commerce.sale_management.module.user_service.dto.UserDTO;
import org.commerce.sale_management.module.user_service.request.user.UserRequest;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:16 PM
 */
public interface UserService {
    List<UserDTO.view> index(UserRequest.save req);
    String save(UserRequest.save req);
    String update(UserRequest.update req);
    String disabled(Long id);
}
