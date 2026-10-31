package org.commerce.sale_management.repository;

import org.commerce.sale_management.model.Employee;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/26/2026 7:20 PM
 */
public interface EmployeeRepository extends BaseCombineRepositorySpecification<Employee, Long> {
    Optional<Employee> findByLineManager(Long id);
    boolean existsBynId(String nid);
}
