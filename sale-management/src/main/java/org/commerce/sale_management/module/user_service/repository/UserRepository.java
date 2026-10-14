package org.commerce.sale_management.module.user_service.repository;

import org.commerce.sale_management.module.user_service.constant.UserRole;
import org.commerce.sale_management.module.user_service.model.User;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/3/2026 7:53 PM
 */

@Repository
public interface UserRepository extends BaseCombineRepositorySpecification<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByRole(UserRole role);
}
