package org.commerce.sale_management.dto;

import lombok.Getter;
import org.commerce.sale_management.model.Order;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 11:01 PM
 */
@Getter
public class OrderDTO extends Order implements BaseView {
    public OrderDTO(Order org){
        this.copyAllIncludeProperties(org);
    }
}
