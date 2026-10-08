package org.commerce.sale_management.module.user_service.specification.filter;

import lombok.Getter;
import lombok.Setter;
import org.module.publish_service.request.BaseInternalRequestFilter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 3:28 PM
 */
@Getter
@Setter
public class UserAddressRequestFilter extends BaseInternalRequestFilter {
    private String testAddress;
}
