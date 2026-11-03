package org.commerce.sale_management.service.impl.order;

import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.constant.OrderConstant;
import org.commerce.sale_management.model.Customer;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.Order;
import org.commerce.sale_management.repository.OrderRepository;
import org.commerce.sale_management.service.CustomerService;
import org.commerce.sale_management.service.DinningTableService;
import org.commerce.sale_management.service.MenuItemService;
import org.commerce.sale_management.service.OrderService;
import org.commerce.sale_management.specification.OrderSpecification;
import org.commerce.sale_management.specification.filter.OrderRequestFilter;
import org.commerce.sale_management.vo.order.OrderRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.module.publish_service.util.EnumUtil;
import org.module.publish_service.util.GenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/3/2026 10:54 PM
 */
@Service
public class OrderServiceImpl extends BaseInternalServiceImpl<Order, Long, OrderRepository, OrderRequestFilter, OrderSpecification> implements OrderService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DinningTableService dinningTableService;

    @Autowired
    private MenuItemService menuItemService;

    @Autowired
    private EnumUtil enumUtil;


    @Override
    public Order order(OrderRequest request) {
        Order order = mapSaveData(request);
        return save(order);
    }

    @Override
    public Order update(OrderRequest request) {
        Assert.notNull(request.getId(), "id is required.");
        Order order = mapSaveData(request);
        return update(order);
    }

    private Order mapSaveData(OrderRequest request) {
        Order order = this.getOneOrNew(request.getId());
        String code = generateCode();
        Customer customer = getCustomer(request);
        DinningTable table = getTableOrThrow(request.getTableId());
        OrderConstant.PaymentStatus payment = getPaymentOrThrow(request.getPaymentStatus());
        CustomerConstant.Type customerType = getCustomerTypeOrThrow(request.getCustomerType());
        OrderConstant.Status status = getStatusOrThrow(request.getStatus());

        order.setOrderNumber(code);
        order.setStatus(status);
        order.setCustomerType(customerType);
        order.setPaymentStatus(payment);
        order.setOrderDate(LocalDateTime.now());
        order.setTable(table);
        order.setCustomer(customer);
    }

    private Customer getCustomer(OrderRequest request) {
        CustomerConstant.Type type = getCustomerTypeOrThrow(request.getCustomerType());
        boolean isStaff = CustomerConstant.STAFF.equals(type.getDescription());
        if (isStaff) {
            return customerService.getCustomerCode(request.getCustomerCode()).orElseThrow(() -> new ApiResponseHandler("Staff code not found."));
        } else {
            return customerService.getCustomerCode(request.getCustomerCode()).orElse(null);
        }
    }

    private OrderConstant.Status getStatusOrThrow(String status) {
        return enumUtil.validate(OrderConstant.Status.class, status);
    }

    private DinningTable getTableOrThrow(Long id) {
        return dinningTableService.getOneOrThrow(id);
    }

    private CustomerConstant.Type getCustomerTypeOrThrow(String type) {
        return enumUtil.validate(CustomerConstant.Type.class, type);
    }

    private OrderConstant.PaymentStatus getPaymentOrThrow(String paymentType) {
        return enumUtil.validate(OrderConstant.PaymentStatus.class, paymentType);
    }

    private Order getOneOrNew(Long id) {
        return Optional.ofNullable(id).map(this::findOneOrThrow).orElseGet(Order::new);
    }

    private String generateCode() {
        String code = GenerateUtil.generateRandomCode(Order.class.getSimpleName(), 6);
        boolean existed = repository.existsByOrderNumber(code);
        if (existed) {
            generateCode();
        }
        return code;
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
