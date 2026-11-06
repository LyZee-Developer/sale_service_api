package org.commerce.sale_management.service.impl.order;

import jakarta.transaction.Transactional;
import org.commerce.sale_management.constant.CustomerConstant;
import org.commerce.sale_management.constant.OrderConstant;
import org.commerce.sale_management.constant.PaymentConstant;
import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.enumuration.CustomerEnum;
import org.commerce.sale_management.enumuration.OrderEnum;
import org.commerce.sale_management.enumuration.PaymentEnum;
import org.commerce.sale_management.model.*;
import org.commerce.sale_management.repository.OrderItemRepository;
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
import java.util.Collections;
import java.util.List;
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
    private OrderItemRepository orderItemRepository;

    @Autowired
    private EnumUtil enumUtil;


    @Override
    @Transactional
    public Order order(OrderRequest request) {
        Assert.notNull(request.getTableId(), "Table id is required.");
        Order order = null;

        List<Order> orders = repository.isTableOrdered(request.getTableId(), LocalDateTime.now());
        if (orders.isEmpty()) {
            order = mapSaveData(request);
            this.save(order);

            /*
             * save order item
             */
            List<OrderItem> items = saveOrderItem(order, request.getOrderItems());
            orderItemRepository.saveAll(items);
        } else {
            /*
             * Add new orders
             */
            Order ord = orders.getFirst();
            List<OrderItem> items = saveOrderItem(ord, request.getOrderItems());
            ord.getOrders().addAll(items);
            order = ord;
            orderItemRepository.saveAll(ord.getOrders());
        }
        return order;
    }

    @Override
    @Transactional
    public Order update(OrderRequest request) {
        Assert.notNull(request.getId(), "id is required.");
        Order order = mapSaveData(request);

        /*
         * replace order items: clearing the collection deletes the old rows (orphanRemoval)
         */
        List<OrderItem> items = saveOrderItem(order, request.getOrderItems());
        order.getOrders().clear();
        orderItemRepository.saveAll(items);
        order.getOrders().addAll(items);
        return update(order);
    }

    @Override
    public List<DinningTableDTO> getAvailableTable() {
        List<Order> orders = repository.tableIsBusy(LocalDateTime.now());
        List<Long> tables = Optional.ofNullable(orders).orElse(List.of()).stream().map(Order::getTable).map(DinningTable::getId).toList();
        return dinningTableService.availableTable(tables);
    }

    private Order mapSaveData(OrderRequest request) {

        Order order = this.getOneOrNew(request.getId());
        boolean isNew = order.getId() == null;
        Customer customer = getCustomer(request);
        DinningTable table = getTableOrThrow(request.getTableId());

        // only check the table when it is a new order or the order moves to another table
        boolean tableChanged = isNew || order.getTable() == null || !order.getTable().getId().equals(table.getId());
        if (tableChanged) {
            validateTableIsOrdered(table);
        }
        PaymentEnum.PaymentStatus payment = getPaymentOrThrow(request.getPaymentStatus(), request.getId());
        CustomerEnum.Type customerType = getCustomerTypeOrThrow(request.getCustomerType());
        OrderEnum.Status status = getStatusOrThrow(request.getStatus(), request.getId());

        // keep the original order number and date when updating
        if (isNew) {
            order.setOrderNumber(generateUniqueCode());
            order.setOrderDate(LocalDateTime.now());
        }
        order.setStatus(status);
        order.setCustomerType(customerType);
        order.setPaymentStatus(payment);
        order.setTable(table);
        order.setCustomer(customer);
        return order;
    }

    private void validateTableIsOrdered(DinningTable table) {
        List<Order> orders = repository.isTableOrdered(table.getId(), LocalDateTime.now());
        if (!orders.isEmpty()) {
            throw new ApiResponseHandler("The table is busy.");
        }

    }

    @Override
    public List<OrderItem> saveOrderItem(Order order, List<OrderRequest.OrderItemRequest> items) {
        validateItemOrThrow(items);
        if (order == null) {
            throw new ApiResponseHandler("order is must not null.");
        }
        return items.stream().map((s) -> this.mapMenuItemData(order, s)).toList();
    }

    private OrderItem mapMenuItemData(Order order, OrderRequest.OrderItemRequest item) {
        MenuItem menuItem = menuItemService.getOneOrThrow(item.getItemId());

        return OrderItem.builder()
            .item(menuItem)
            .order(order)
            .isCountIn(item.getIsCountIn())
            .qty(item.getQty())
            .unitPrice(item.getUnitPrice())
            .specialInstructions(item.getSpecialInstructions())
            .build();

    }

    private void validateItemOrThrow(List<OrderRequest.OrderItemRequest> items) {
        if (items.isEmpty()) {
            throw new ApiResponseHandler("Your order item is empty.");
        }
        for (OrderRequest.OrderItemRequest item : items) {
            MenuItem itm = menuItemService.getOneOrThrow(item.getItemId());
            if (!itm.getIsAvailable()) {
                throw new ApiResponseHandler("The item [%s] is not available now.".formatted(itm.getName()));
            }
        }
    }

    private Customer getCustomer(OrderRequest request) {
        CustomerEnum.Type type = getCustomerTypeOrThrow(request.getCustomerType());
        boolean isStaff = CustomerConstant.STAFF.equals(type.getDescription());
        if (isStaff) {
            return customerService.getCustomerCode(request.getCustomerCode()).orElseThrow(() -> new ApiResponseHandler("Staff code not found."));
        } else {
            return customerService.getCustomerCode(request.getCustomerCode()).orElse(null);
        }
    }

    private OrderEnum.Status getStatusOrThrow(String status, Long id) {
        OrderEnum.Status stt = enumUtil.validate(OrderEnum.Status.class, status);
        if (id == null && stt.getDescription().equals(OrderConstant.END)) {
            throw new ApiResponseHandler("This order did not start yet.");
        }
        return stt;
    }

    private DinningTable getTableOrThrow(Long id) {
        return dinningTableService.getOneOrThrow(id);
    }

    private CustomerEnum.Type getCustomerTypeOrThrow(String type) {
        return enumUtil.validate(CustomerEnum.Type.class, type);
    }

    private PaymentEnum.PaymentStatus getPaymentOrThrow(String paymentType, Long id) {
        PaymentEnum.PaymentStatus stt = enumUtil.validate(PaymentEnum.PaymentStatus.class, paymentType);
        if (id == null && stt.getDescription().equals(PaymentConstant.PAID)) {
            throw new ApiResponseHandler("This order did not start order yet.");
        }
        return stt;
    }

    private Order getOneOrNew(Long id) {
        return Optional.ofNullable(id).map(this::findOneOrThrow).orElseGet(Order::new);
    }

    private String generateUniqueCode() {
        String code = GenerateUtil.generateRandomCode(Order.class.getSimpleName(), 6);
        boolean existed = repository.existsByOrderNumber(code);
        return existed ? generateUniqueCode() : code;
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
