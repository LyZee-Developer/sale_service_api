package org.commerce.sale_management.dto;

import org.commerce.sale_management.model.Customer;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 7:35 PM
 */
public class CustomerDTO extends Customer implements BaseView {
    public CustomerDTO(Customer org) {
        this.copyAllProperties(org);
    }
}
