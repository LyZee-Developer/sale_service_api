package org.commerce.sale_management.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import org.commerce.sale_management.model.Employee;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 10:56 PM
 */
@Getter
//@JsonIncludeProperties({"firstNameEn","lastNameKh"})
public class EmployeeViewDTO extends Employee implements BaseView {

    public EmployeeViewDTO(Employee employee) {
        this.copyAllIncludeProperties(employee);
    }
}
