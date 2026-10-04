package org.commerce.sale_management.module.user_service.service.impl.user;

import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.module.user_service.dto.UserDTO;
import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.repository.UserRepository;
import org.commerce.sale_management.module.user_service.request.user.UserRequest;
import org.commerce.sale_management.module.user_service.service.UserService;
import org.commerce.sale_management.module.user_service.service.impl.BaseInternalServiceImpl;
import org.commerce.sale_management.module.user_service.specification.UserSpecification;
import org.commerce.sale_management.module.user_service.specification.filter.UserRequestFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:18 PM
 */
@Service
public class UserServiceImpl extends BaseInternalServiceImpl<User, Long, UserRepository, UserRequestFilter, UserSpecification> implements UserService {

    @Override
    public List<UserDTO.view> index(UserRequest.save req) {
        return List.of();
    }

    @Override
    public String save(UserRequest.save req) {
        User user = User.builder()
                .username(req.getUsername())
                .password(req.getPassword())
                .build();
        this.save(user);
        return "create success";
    }

    @Override
    public String update(UserRequest.update req) {
        User user = this.findOneThrow(req.getId());
        this.save(user);
        return "update success";
    }

    @Override
    public String disabled(Long id) {
        User user = this.findOneThrow(id);
        user.setIsActivate(Boolean.FALSE);
        this.save(user);
        return "disabled user!";
    }
}
