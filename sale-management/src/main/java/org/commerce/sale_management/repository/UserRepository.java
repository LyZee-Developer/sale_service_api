package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.User;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;

import java.util.List;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 10:13 PM
 */
public interface UserRepository extends BaseCombineRepositorySpecification<User, Long> {
    Optional<User> findByUsernameAndIsActivateTrue(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmployeeId(Long employeeId);
    Optional<List<User>> findAllByAttempt(Long attempt);

}
