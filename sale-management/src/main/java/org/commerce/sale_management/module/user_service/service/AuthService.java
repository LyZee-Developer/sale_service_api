package org.commerce.sale_management.module.user_service.service;

import org.commerce.sale_management.module.user_service.dto.AuthDTO;
import org.commerce.sale_management.module.user_service.dto.UserDTO;
import org.commerce.sale_management.module.user_service.request.auth.AuthRequest;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
public interface AuthService {
    /** Empty when the username/password is wrong or the user is disabled. */
    Optional<AuthDTO.token> login(AuthRequest.login req);
    UserDTO.view me();
}
