package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import org.commerce.sale_management.constant.OrderConst.PaymentStatus;
import org.commerce.sale_management.constant.OrderConst.Status;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.Order}
 **/
@StaticMetamodel(Order.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Order_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #orderNumber
	 **/
	public static final String ORDER_NUMBER = "orderNumber";
	
	/**
	 * @see #status
	 **/
	public static final String STATUS = "status";
	
	/**
	 * @see #paymentStatus
	 **/
	public static final String PAYMENT_STATUS = "paymentStatus";
	
	/**
	 * @see #orderDate
	 **/
	public static final String ORDER_DATE = "orderDate";
	
	/**
	 * @see #table
	 **/
	public static final String TABLE = "table";
	
	/**
	 * @see #customer
	 **/
	public static final String CUSTOMER = "customer";
	
	/**
	 * @see #orders
	 **/
	public static final String ORDERS = "orders";
	
	/**
	 * @see #orderHistories
	 **/
	public static final String ORDER_HISTORIES = "orderHistories";
	
	/**
	 * @see #kitchenTickets
	 **/
	public static final String KITCHEN_TICKETS = "kitchenTickets";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.Order}
	 **/
	public static volatile EntityType<Order> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#orderNumber}
	 **/
	public static volatile SingularAttribute<Order, String> orderNumber;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#status}
	 **/
	public static volatile SingularAttribute<Order, Status> status;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#paymentStatus}
	 **/
	public static volatile SingularAttribute<Order, PaymentStatus> paymentStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#orderDate}
	 **/
	public static volatile SingularAttribute<Order, LocalDateTime> orderDate;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#table}
	 **/
	public static volatile SingularAttribute<Order, DinningTable> table;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#customer}
	 **/
	public static volatile SingularAttribute<Order, Customer> customer;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#orders}
	 **/
	public static volatile ListAttribute<Order, OrderItem> orders;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#orderHistories}
	 **/
	public static volatile ListAttribute<Order, OrderHistory> orderHistories;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Order#kitchenTickets}
	 **/
	public static volatile ListAttribute<Order, KitchenTicket> kitchenTickets;

}

