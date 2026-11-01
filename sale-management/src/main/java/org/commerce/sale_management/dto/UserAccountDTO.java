package org.commerce.sale_management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commerce.sale_management.constant.EmployeeConst;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.model.User;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/31/2026 10:51 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class UserAccountDTO {
    private String fullName;
    private String fullNameEn;
    private String email;
    private String status;
    private String username;
    private String password;
    private String employeeCode;
    private Long id;
    private boolean resetAttempt;
    private boolean allowResetPassword;

    public UserAccountDTO(User user) {
        Employee employee = user.getEmployee();
        this.employeeCode = employee.getCode();
        this.fullName = "%s %s".formatted(employee.getFirstName(), employee.getLastName());
        this.fullNameEn = "%s %s".formatted(employee.getFirstNameEn(), employee.getLastNameEn());
        this.email = employee.getEmail();
        this.status = employee.getStatus().getDescription();
        this.id = employee.getId();
        this.password = user.getPasswordHash();
        this.username = user.getUsername();
        this.resetAttempt = user.getAttempt() == 3;
        this.allowResetPassword = user.getIsAllowResetPw();
    }
}
