package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.enumuration.CustomerEnum;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 6:03 AM
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BasePrimaryIdEntity {
    @NotBlank
    private String code;
    @NotBlank
    private String fullName;
    private String fullNameEn;
    @NotBlank
    private String phone;
    private String phone1;
    private Boolean gender;
    private String email;

    @Enumerated(EnumType.STRING)
    private CustomerEnum.Type type;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIncludeProperties({
        "code",
        "firstName",
        "lastName",
        "firstNameEn",
        "lastNameEn",
        "email",
        "phone",
        "phone1",
    })
    private Employee employee;
}
