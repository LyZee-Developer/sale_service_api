package org.commerce.sale_management.module.user_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.module.publish_service.model.BasePrimaryIdEntity;

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
public class User extends BasePrimaryIdEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
}
