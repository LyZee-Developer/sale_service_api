package org.commerce.sale_management;

import jakarta.persistence.EntityManager;
import org.commerce.sale_management.module.menu_service.model.MenuCategory;
import org.commerce.sale_management.module.menu_service.model.MenuItem;
import org.commerce.sale_management.module.order_service.constant.KitchenStatus;
import org.commerce.sale_management.module.order_service.constant.OrderStatus;
import org.commerce.sale_management.module.order_service.constant.OrderType;
import org.commerce.sale_management.module.order_service.model.Order;
import org.commerce.sale_management.module.order_service.model.OrderItem;
import org.commerce.sale_management.module.order_service.model.OrderItemStatusHistory;
import org.commerce.sale_management.module.order_service.model.OrderStatusHistory;
import org.commerce.sale_management.module.payment_service.constant.OrderPaymentStatus;
import org.commerce.sale_management.module.payment_service.constant.PaymentMethod;
import org.commerce.sale_management.module.payment_service.constant.PaymentStatus;
import org.commerce.sale_management.module.payment_service.model.Payment;
import org.commerce.sale_management.module.table_service.constant.TableStatus;
import org.commerce.sale_management.module.table_service.model.RestaurantTable;
import org.commerce.sale_management.module.user_service.constant.UserRole;
import org.commerce.sale_management.module.user_service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Phase 1: verifies the Flyway schema matches the entities (ddl-auto=validate boots the context)
 * and that the database constraints reject invalid data. Each test rolls back.
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RestaurantSchemaTests {

    @Autowired
    private EntityManager em;

    @Autowired
    private JdbcTemplate jdbc;

    @Test
    void persistsFullOrderGraph() {
        User waiter = user("waiter1");
        MenuItem rice = menuItem(category("Main"), "Fried rice", "4.50");
        RestaurantTable table = table("T1");

        Order order = order("ORD-0001", waiter, table);
        order.getOrderItems().add(item(order, rice, 2, "4.50", "9.00"));
        order.getOrderItems().add(item(order, rice, 1, "4.50", "4.50"));
        em.persist(order);

        Instant paidAt = Instant.now().truncatedTo(ChronoUnit.MICROS);
        em.persist(Payment.builder().order(order).amount(new BigDecimal("13.50"))
                .paymentMethod(PaymentMethod.CASH).paymentStatus(PaymentStatus.COMPLETED)
                .receivedBy(waiter).paidAt(paidAt).build());
        em.persist(OrderStatusHistory.builder().order(order).newStatus(OrderStatus.PENDING)
                .changedBy(waiter).changedAt(paidAt).build());
        em.persist(OrderItemStatusHistory.builder().orderItem(order.getOrderItems().getFirst())
                .oldStatus(KitchenStatus.PENDING).newStatus(KitchenStatus.PREPARING)
                .changedBy(waiter).changedAt(paidAt).build());
        em.flush();
        em.clear();

        Order reloaded = em.find(Order.class, order.getId());
        assertEquals(2, reloaded.getOrderItems().size());
        assertEquals(0L, reloaded.getVersion());
        assertEquals(new BigDecimal("13.50"), reloaded.getTotalAmount());
        assertEquals("Fried rice", reloaded.getOrderItems().getFirst().getItemName());

        Instant storedPaidAt = jdbc.queryForObject(
                "select paid_at from payments where order_id = ?",
                (rs, i) -> rs.getObject(1, java.time.OffsetDateTime.class).toInstant(), order.getId());
        assertEquals(paidAt, storedPaidAt);
    }

    @Test
    void rejectsNegativeMenuPrice() {
        MenuCategory category = category("Drinks");
        assertThrows(DataIntegrityViolationException.class, () ->
                jdbc.update("insert into menu_items (category_id, name, price) values (?, 'Tea', -1)", category.getId()));
    }

    @Test
    void rejectsNonPositiveQuantity() {
        Order order = persistedOrder("ORD-Q");
        assertThrows(DataIntegrityViolationException.class, () -> insertItem(order.getId(), 0, "1.00", "0.00"));
    }

    @Test
    void rejectsLineTotalThatDoesNotMatchQuantityTimesPrice() {
        Order order = persistedOrder("ORD-L");
        assertThrows(DataIntegrityViolationException.class, () -> insertItem(order.getId(), 2, "1.00", "5.00"));
    }

    @Test
    void rejectsDuplicateOrderNumber() {
        Order first = persistedOrder("ORD-DUP");
        assertThrows(DataIntegrityViolationException.class, () -> jdbc.update(
                "insert into orders (order_number, created_by_user_id, order_type, order_status, payment_status, subtotal, total_amount) "
                        + "values ('ORD-DUP', ?, 'TAKEAWAY', 'PENDING', 'UNPAID', 0, 0)",
                first.getCreatedByUser().getId()));
    }

    @Test
    void rejectsNonPositivePaymentAmount() {
        Order order = persistedOrder("ORD-P");
        assertThrows(DataIntegrityViolationException.class, () -> jdbc.update(
                "insert into payments (order_id, amount, payment_method, payment_status) values (?, 0, 'CASH', 'COMPLETED')",
                order.getId()));
    }

    @Test
    void rejectsUnknownOrderStatus() {
        Order order = persistedOrder("ORD-S");
        assertThrows(DataIntegrityViolationException.class, () ->
                jdbc.update("update orders set order_status = 'SHIPPED' where id = ?", order.getId()));
    }

    // ----- fixtures -----

    private Order persistedOrder(String orderNumber) {
        Order order = order(orderNumber, user("u-" + orderNumber), null);
        em.persist(order);
        em.flush();
        return order;
    }

    private void insertItem(Long orderId, int quantity, String unitPrice, String lineTotal) {
        jdbc.update("insert into order_items (order_id, item_name, quantity, unit_price, line_total, kitchen_status) "
                        + "values (?, 'X', ?, ?, ?, 'PENDING')",
                orderId, quantity, new BigDecimal(unitPrice), new BigDecimal(lineTotal));
    }

    private User user(String username) {
        User user = User.builder().username(username).password("{bcrypt}hash")
                .fullName("Test " + username).role(UserRole.WAITER).build();
        em.persist(user);
        return user;
    }

    private MenuCategory category(String name) {
        MenuCategory category = MenuCategory.builder().name(name).build();
        em.persist(category);
        em.flush();
        return category;
    }

    private MenuItem menuItem(MenuCategory category, String name, String price) {
        MenuItem item = MenuItem.builder().category(category).name(name).price(new BigDecimal(price)).build();
        em.persist(item);
        return item;
    }

    private RestaurantTable table(String number) {
        RestaurantTable table = RestaurantTable.builder().tableNumber(number).capacity(4)
                .status(TableStatus.AVAILABLE).build();
        em.persist(table);
        return table;
    }

    private Order order(String orderNumber, User createdBy, RestaurantTable table) {
        return Order.builder().orderNumber(orderNumber).createdByUser(createdBy).table(table)
                .orderType(table == null ? OrderType.TAKEAWAY : OrderType.DINE_IN)
                .orderStatus(OrderStatus.PENDING).paymentStatus(OrderPaymentStatus.UNPAID)
                .subtotal(new BigDecimal("13.50")).totalAmount(new BigDecimal("13.50")).build();
    }

    private OrderItem item(Order order, MenuItem menuItem, int quantity, String unitPrice, String lineTotal) {
        return OrderItem.builder().order(order).menuItem(menuItem).itemName(menuItem.getName())
                .quantity(quantity).unitPrice(new BigDecimal(unitPrice)).lineTotal(new BigDecimal(lineTotal))
                .kitchenStatus(KitchenStatus.PENDING).build();
    }
}
