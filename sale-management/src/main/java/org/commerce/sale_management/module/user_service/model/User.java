package org.commerce.sale_management.module.user_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.commerce.sale_management.module.user_service.constant.UserRole;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/3/2026 7:52 PM
 */
@Entity
@Getter
@Table(name = "app_user")
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BasePrimaryIdEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 150)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserRole role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserAddress> userAddressList;
}
