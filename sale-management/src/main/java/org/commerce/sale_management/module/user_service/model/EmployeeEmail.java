package org.commerce.sale_management.module.user_service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/3/2026 7:52 PM
 */
@Entity
@Getter
@Table(name = "test_employee_email")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEmail extends BasePrimaryIdEntity {
    @Column(unique = true)
    private String email;
    private String facebook;
    private String phone;
    private String phone1;
}
