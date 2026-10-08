package org.commerce.sale_management.module.user_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/6/2026 5:56 AM
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserAddress extends BasePrimaryIdEntity {
    private String facebook;
    private String email;
    private String phone;
    private String phone1;
    private String address;

    @ManyToOne
    @JsonBackReference
    private User user;
}
