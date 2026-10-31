package org.commerce.sale_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.commerce.sale_management.constant.CustomerConst;
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
    private String fullNameKh;
    private String fullNameEn;
    @NotBlank
    private String phone;
    private String phone1;
    private Boolean gender;
    private Boolean email;

    @Enumerated(EnumType.STRING)
    private CustomerConst.Type type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
