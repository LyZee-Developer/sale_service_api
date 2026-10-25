package org.commerce.sale_management.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.io.Serializable;
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
public class User extends BasePrimaryIdEntity  implements Serializable {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, orphanRemoval = true)
    private List<UserAddress> userAddressList;
}
