package org.commerce.sale_management.vo.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.constant.EmployeeConst;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 10:47 PM
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String code;
    @NotBlank(message = "Field first name kh is required.")
    private String firstNameKh;
    @NotBlank(message = "Field last name kh is required.")
    private String lastNameKh;
    private String firstNameEn;
    private String lastNameEn;
    private String email;
    private String phone;
    private String phone1;
    //# 1 male, 0 female
    private Boolean gender = Boolean.TRUE;
    //-- date of birth
    @NotNull
    private LocalDate dob;
    @NotNull
    private LocalDate hireDate;
    private String address;
    @NotBlank(message = "Identity-card ID is required.")
    private String nID;
    private BigDecimal salary;
    private String status = EmployeeConst.Status.ACTIVE.toString();
    private Long departmentId;
    private Long positionId;
    private Long lineManager;

    @Getter
    @Setter
    public static class update extends EmployeeRequest {
        private Long id;
    }
}
