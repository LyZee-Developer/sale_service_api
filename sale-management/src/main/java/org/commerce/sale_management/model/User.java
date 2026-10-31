package org.commerce.sale_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.module.publish_service.model.BasePrimaryIdEntity;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:05 PM
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
@Builder
public class User extends BasePrimaryIdEntity {
    @Column(unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String passwordHash;
    private Integer attempt;

    @ManyToOne
    @JoinColumn
    private Employee employee;
}
