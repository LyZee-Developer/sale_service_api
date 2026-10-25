package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.User;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.springframework.stereotype.Repository;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/3/2026 7:53 PM
 */

@Repository
public interface UserRepository extends BaseCombineRepositorySpecification<User, Long> {

}
