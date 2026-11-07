package org.commerce.sale_management.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.commerce.sale_management.enumuration.CustomerEnum;
import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.model.Floor;
import org.commerce.sale_management.model.Order;
import org.module.publish_service.view.BaseView;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 11:01 PM
 */
@Getter
@JsonIgnoreProperties({
    "table",
    "customer",
})
public class OrderDTO extends Order implements BaseView {
    private final String tableName;
    private final String tableDescription;
    private final String floorName;
    private final String customerName;
    private final String customerEnglishName;
    private final String customerCode;
    private final String customerPhone;

    public OrderDTO(Order org) {
        this.copyAllIncludeProperties(org);
        this.tableName = org.getTable().getTableName();
        this.tableDescription = org.getTable().getDescription();
        this.floorName = Optional.ofNullable(org.getTable().getFloor()).map(Floor::getName).orElse(null);
        this.customerName = Optional.ofNullable(org.getCustomer()).map(Customer::getFullName).orElse(null);
        this.customerEnglishName = Optional.ofNullable(org.getCustomer()).map(Customer::getFullNameEn).orElse(null);
        this.customerCode = Optional.ofNullable(org.getCustomer()).map(Customer::getCode).orElse(null);
        this.customerPhone = Optional.ofNullable(org.getCustomer()).map(Customer::getPhone).orElse(null);
    }
}
