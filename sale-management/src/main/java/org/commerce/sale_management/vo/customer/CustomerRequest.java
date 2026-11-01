package org.commerce.sale_management.vo.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 7:26 PM
 */
@Getter
@Setter
public class CustomerRequest {
    private Long id;
    /*
     *generate auto
     */
    private String code;
    @NotBlank(message = "fullname is required.")
    private String fullName;
    private String fullNameEn;
    @NotBlank(message = "phone is required.")
    private String phone;
    private String phone1;
    @NotNull
    private Boolean gender;
    private String email;
    @NotBlank(message = "type is required.")
    private String type;
    private Long employeeId;

}
