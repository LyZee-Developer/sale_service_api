package org.commerce.sale_management.module.user_service.service;

import org.commerce.sale_management.module.user_service.model.UserAddress;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/6/2026 6:27 AM
 */
public interface UserAddressService {
    List<UserAddress> save(List<UserAddress> add);
}
