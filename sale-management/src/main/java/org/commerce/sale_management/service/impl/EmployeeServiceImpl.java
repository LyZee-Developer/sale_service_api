package org.commerce.sale_management.service.impl;

import jakarta.transaction.Transactional;
import org.commerce.sale_management.constant.EmployeeConst;
import org.commerce.sale_management.dto.EmployeeViewDTO;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.repository.EmployeeRepository;
import org.commerce.sale_management.service.EmployeeService;
import org.commerce.sale_management.specification.EmployeeSpecification;
import org.commerce.sale_management.specification.filter.EmployeeRequestFilter;
import org.commerce.sale_management.vo.employee.EmployeeRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ConflictException;
import org.module.publish_service.exception.ResourceNotFoundException;
import org.module.publish_service.response.ApiResponse;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.module.publish_service.util.EnumUtil;
import org.module.publish_service.util.GenerateUtil;
import org.module.publish_service.util.SensitiveUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/28/2026 11:03 PM
 */
@Service
public class EmployeeServiceImpl extends BaseInternalServiceImpl<Employee, Long, EmployeeRepository, EmployeeRequestFilter, EmployeeSpecification> implements EmployeeService {
    @Autowired
    private GenerateUtil generateUtil;

    @Autowired
    private SensitiveUtil sensitiveUtil;

    @Autowired
    private ApiResponse apiResponse;


    @Autowired
    private EnumUtil enumUtil;

    @Override
    public List<EmployeeViewDTO> list(EmployeeRequestFilter filter, Pageable pageable) {
        return this.findAllView(EmployeeViewDTO.class);
    }

    @Override
    @Transactional
    public EmployeeViewDTO save(EmployeeRequest request) {
        Employee emp = saveInfo(new Employee(), request);
        save(emp);
        return new EmployeeViewDTO(emp);
    }

    @Override
    public EmployeeViewDTO getOne(Long id) {
        return this.findOne(id).map(EmployeeViewDTO::new).orElse(null);
    }


    @Override
    public Employee update(Long id, EmployeeRequest request) {
        Employee emp = this.findOneOrThrow(id);
        return saveInfo(emp, request);
    }

    public Employee saveInfo(Employee emp, EmployeeRequest request) {
        //validate
        validateLineManager(request);
        validateNId(request);

        String generateCode = generateUtil.generateNameWithDateTime(Employee.class.getSimpleName());
        String encryptSalary = sensitiveUtil.encryptNumber(request.getSalary());
        EmployeeConst.Status status = enumUtil.validate(EmployeeConst.Status.class, request.getStatus());
        Employee lineManager = this.findOne(request.getLineManager()).orElse(null);

        emp.setCode(generateCode);
        emp.setFirstNameKh(request.getFirstNameKh());
        emp.setLastNameKh(request.getLastNameKh());
        emp.setFirstNameEn(request.getFirstNameEn());
        emp.setLastNameEn(request.getLastNameEn());
        emp.setEmail(request.getEmail());
        emp.setPhone(request.getPhone());
        emp.setPhone1(request.getPhone1());
        emp.setGender(request.getGender());
        emp.setDob(request.getDob());
        emp.setHireDate(request.getHireDate());
        emp.setAddress(request.getAddress());
        emp.setNId(request.getNID());
        emp.setSalary(encryptSalary);
        emp.setStatus(status);
        emp.setDepartmentId(request.getDepartmentId());
        emp.setPositionId(request.getPositionId());
        emp.setLineManager(lineManager);
        return emp;
    }

    private void validateLineManager(EmployeeRequest request) {
        boolean exists = repository.existsBynId(request.getNID());
        if (exists) {
            throw new ConflictException("NID have already exist.");
        }
    }

    private void validateNId(EmployeeRequest request) {
        Long lineManagerId = request.getLineManager();
        if (lineManagerId == null) {
            return;
        }
        this.findOne(lineManagerId).orElseThrow(() -> new ResourceNotFoundException("Line manager not found!"));
    }

    @Override
    public String delete(Long id) {
        Employee emp = this.findOneOrThrow(id);
        emp.setIsActivate(Boolean.FALSE);
        return "delete successfully.";
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
