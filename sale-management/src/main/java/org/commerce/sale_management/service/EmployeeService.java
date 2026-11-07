package org.commerce.sale_management.service;

import org.commerce.sale_management.dto.EmployeeDTO;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.specification.filter.EmployeeRequestFilter;
import org.commerce.sale_management.vo.employee.EmployeeRequest;
import org.module.publish_service.service.BaseInternalService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 10:54 PM
 */
public interface EmployeeService extends BaseInternalService<Employee, Long, EmployeeRequestFilter> {
    List<EmployeeDTO> list(EmployeeRequestFilter request, Pageable pageable);

    EmployeeDTO save(EmployeeRequest request);

    EmployeeDTO getOneById(Long id);

    Employee update(Long id, EmployeeRequest request);

    String delete(Long id);
}
