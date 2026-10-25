package org.commerce.sale_management.service.impl;

import org.commerce.sale_management.model.UserAddress;
import org.commerce.sale_management.repository.UserAddressRepository;
import org.commerce.sale_management.service.BaseInternalAppService;
import org.commerce.sale_management.service.UserAddressService;
import org.commerce.sale_management.specification.UserAddressSpecification;
import org.commerce.sale_management.specification.filter.UserAddressRequestFilter;
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
