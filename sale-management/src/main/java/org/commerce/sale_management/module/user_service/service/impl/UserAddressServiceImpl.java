package org.commerce.sale_management.module.user_service.service.impl;

import org.commerce.sale_management.module.user_service.model.UserAddress;
import org.commerce.sale_management.module.user_service.repository.UserAddressRepository;
import org.commerce.sale_management.module.user_service.service.BaseInternalAppService;
import org.commerce.sale_management.module.user_service.service.UserAddressService;
import org.commerce.sale_management.module.user_service.specification.UserAddressSpecification;
import org.commerce.sale_management.module.user_service.specification.filter.UserAddressRequestFilter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/6/2026 6:28 AM
 */
@Service
public class UserAddressServiceImpl extends BaseInternalAppService<UserAddress,Long, UserAddressRepository, UserAddressRequestFilter, UserAddressSpecification> implements UserAddressService {
    @Override
    public List<UserAddress> save(List<UserAddress> add) {
        return this.saveAll(add);
    }
}
