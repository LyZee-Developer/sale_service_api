package org.commerce.sale_management.service.impl.user;

import jakarta.transaction.Transactional;
import org.commerce.sale_management.config.RedisCacheConfig;
import org.commerce.sale_management.dto.UserDTO;
import org.commerce.sale_management.model.User;
import org.commerce.sale_management.model.UserAddress;
import org.commerce.sale_management.repository.UserRepository;
import org.commerce.sale_management.user.UserRequest;
import org.commerce.sale_management.service.BaseInternalAppService;
import org.commerce.sale_management.service.UserAddressService;
import org.commerce.sale_management.service.UserService;
import org.commerce.sale_management.specification.UserSpecification;
import org.commerce.sale_management.specification.filter.UserRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:18 PM
 */
@Service
public class UserServiceImpl extends BaseInternalAppService<User, Long, UserRepository, UserRequestFilter, UserSpecification> implements UserService {

    @Autowired
    private UserAddressService userAddressService;

    @Override
    public Page<User> pageUser(Pageable pageable) {
        return this.findAll(pageable);
    }

    @Override
    @Cacheable(cacheNames = RedisCacheConfig.USER_ID_CACHE, key = "#id")
    public User findById(Long id) {
        return this.findOneThrow(id);
    }

    @Override
    public Page<User> allData(UserRequestFilter filter, Pageable pageable) {
        return this.findAllSpePageFetch(filter, pageable);
    }

    @Override
    public List<UserDTO.view> index() {
        return this.findAllView(UserDTO.view.class);
    }

    @Transactional
    @Override
    public String save(UserRequest.save req) {
        User user = User.builder()
                .username(req.getUsername())
                .password(req.getPassword())
                .build();
        this.save(user);

        List<UserAddress> userAddressList = Optional.ofNullable(req.getUserAddress())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(s-> {
                    UserAddress add = new UserAddress();
                    add.setFacebook(s.getFacebook());
                    add.setEmail(s.getEmail());
                    add.setPhone(s.getPhone());
                    add.setPhone1(s.getPhone1());
                    add.setAddress(s.getAddress());
                    add.setUser(user);
                    return add;
                }).toList();
        userAddressService.save(userAddressList);
        return "create success";
    }

    @Override
    @CacheEvict(cacheNames = RedisCacheConfig.USER_ID_CACHE, key = "#req.id")
    public String update(UserRequest.update req) {
        User user = this.findOneThrow(req.getId());
        this.save(user);
        return "update success";
    }

    @Override
    @CacheEvict(cacheNames = RedisCacheConfig.USER_ID_CACHE, key = "#id")
    public String disabled(Long id) {
        User user = this.findOneThrow(id);
        user.setIsActivate(Boolean.FALSE);
        this.update(user);
        return "disabled user!";
    }
}
