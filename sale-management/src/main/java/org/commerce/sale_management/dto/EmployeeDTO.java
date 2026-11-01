package org.commerce.sale_management.dto;

import lombok.Getter;
import org.commerce.sale_management.model.Employee;
import org.module.publish_service.view.BaseView;

import java.time.LocalDate;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 10:56 PM
 */
@Getter
public class EmployeeDTO extends Employee implements BaseView {

    private final boolean todayIsBirthday;

    public EmployeeDTO(Employee employee) {
        this.copyAllIncludeProperties(employee);
        this.todayIsBirthday = employee.getDob().equals(LocalDate.now());
    }

}
