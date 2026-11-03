package org.commerce.sale_management.service.impl.customer;

import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.model.Employee;
import org.commerce.sale_management.repository.CustomerRepository;
import org.commerce.sale_management.service.CustomerService;
import org.commerce.sale_management.service.EmployeeService;
import org.commerce.sale_management.specification.CustomerSpecification;
import org.commerce.sale_management.specification.filter.CustomerRequestFilter;
import org.commerce.sale_management.vo.customer.CustomerRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.response.ApiResponse;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.module.publish_service.util.EnumUtil;
import org.module.publish_service.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 7:40 PM
 */
@Service
public class CustomerServiceImpl extends BaseInternalServiceImpl<Customer, Long, CustomerRepository, CustomerRequestFilter, CustomerSpecification> implements CustomerService {
    @Autowired
    private EnumUtil enumUtil;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ApiResponse apiResponse;

    @Override
    public Customer create(CustomerRequest request) {
        Customer customer = mpaInfo(request);
        return save(customer);
    }

    @Override
    public Customer update(CustomerRequest request) {
        this.getOneOrThrow(request.getId());
        Customer customer = mpaInfo(request);
        return update(customer);
    }

    private Customer mpaInfo(CustomerRequest req) {
        enumUtil.validate(CustomerConstant.Type.class, req.getType());
        Employee employee = checkTypeOrGetEmployee(req);
        String code = generateCode();
        Customer customer = getCustomer(req.getId());

        if (ObjectUtils.isEmpty(req.getId())) {
            customer.setCode(code);
        }
        customer.setFullName(req.getFullName());
        customer.setFullNameEn(req.getFullNameEn());
        customer.setPhone(req.getPhone());
        customer.setPhone1(req.getPhone1());
        customer.setGender(req.getGender());
        customer.setEmail(req.getEmail());
        customer.setType(CustomerConstant.Type.valueOf(req.getType()));
        customer.setEmployee(employee);
        return customer;
    }

    private Customer getCustomer(Long id) {
        return Optional.ofNullable(id).map(this::findOneOrThrow).orElseGet(Customer::new);
    }

    private String generateCode() {
        /*
         * ramdom code
         * @Condition if existed run this function again
         */
        String code = GenerateUtil.generateRandomCode(Customer.class.getSimpleName(), 5);
        boolean isExisted = repository.existsByCode(code);
        if (isExisted) {
            generateCode();
        }
        return code;
    }

    private Employee checkTypeOrGetEmployee(CustomerRequest req) {
        boolean isGeneral = CustomerConstant.Type.GENERAL.toString().equals(req.getType());
        if (isGeneral) {
            return null;
        }
        Assert.notNull(req.getEmployeeId(), "Employee id is null.");
        if (!Objects.nonNull(req.getId())) {
            checkIsExistedEmployee(req.getEmployeeId());
        }
        return employeeService.getOneOrThrow(req.getEmployeeId());
    }

    private void checkIsExistedEmployee(Long id) {
        boolean isExisted = this.repository.existsByEmployeeId(id);
        Employee employee = employeeService.getOneOrThrow(id);
        if (isExisted) {
            throw new ApiResponseHandler("The employee [%s %s] have create customer already.".formatted(employee.getFirstNameEn(), employee.getLastNameEn()));
        }
    }

    @Override
    public String changeStatus(Long id, boolean isEnabled) {
        Customer customer = getCustomer(id);
        customer.setIsActivate(isEnabled);
        update(customer);
        return "%s customer success.".formatted(isEnabled ? "Enabled" : "Disabled");
    }

    @Override
    public Optional<Customer> getCustomerCode(String code) {
        return repository.findByCodeAndIsActivateTrue(code);
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
