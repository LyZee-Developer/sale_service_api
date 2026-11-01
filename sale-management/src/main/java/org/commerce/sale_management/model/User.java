package org.commerce.sale_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.commerce.sale_management.vo.userAccount.UserAccount;
import org.module.publish_service.model.BasePrimaryIdEntity;

import java.time.LocalDateTime;

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
    @Min(0)
    private int attempt = 0;

    private LocalDateTime resetAt;
    private Boolean isAllowResetPw = Boolean.FALSE;

    @ManyToOne
    @JoinColumn
    private Employee employee;

    public User(UserAccount.create req, Employee employee) {
        this.username = req.getUsername();
        this.passwordHash = req.getPassword();
        this.employee = employee;
    }
}
