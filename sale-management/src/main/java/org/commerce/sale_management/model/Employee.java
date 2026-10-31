package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.constant.EmployeeConst;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 6:05 AM
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends BasePrimaryIdEntity {
    @Column(unique = true, nullable = false)
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
    private Boolean gender; //-- 1 male, 0 female
    @NotNull
    private LocalDate dob; //-- date of birth
    @NotNull
    private LocalDate hireDate;
    private String address;
    @Column(length = 20, nullable = false)
    private String nId;
    private String salary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeConst.Status status;

    private Long departmentId;
    private Long positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_manager_id")
    @JsonBackReference
    private Employee lineManager;

    @OneToMany(mappedBy = Employee_.LINE_MANAGER, orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIncludeProperties({
        Employee_.FIRST_NAME_EN,
        Employee_.LAST_NAME_EN,
        Employee_.LAST_NAME_KH,
        Employee_.LAST_NAME_KH,
        Employee_.EMAIL,
        Employee_.CODE,
        Employee_.GENDER
    })
    private List<Employee> employees;
}
