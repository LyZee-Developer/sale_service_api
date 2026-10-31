package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.OrderHistory}
 **/
@StaticMetamodel(OrderHistory.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class OrderHistory_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #oldStatus
	 **/
	public static final String OLD_STATUS = "oldStatus";
	
	/**
	 * @see #newStatus
	 **/
	public static final String NEW_STATUS = "newStatus";
	
	/**
	 * @see #oldPaymentStatus
	 **/
	public static final String OLD_PAYMENT_STATUS = "oldPaymentStatus";
	
	/**
	 * @see #newPaymentStatus
	 **/
	public static final String NEW_PAYMENT_STATUS = "newPaymentStatus";
	
	/**
	 * @see #order
	 **/
	public static final String ORDER = "order";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.OrderHistory}
	 **/
	public static volatile EntityType<OrderHistory> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderHistory#oldStatus}
	 **/
	public static volatile SingularAttribute<OrderHistory, String> oldStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderHistory#newStatus}
	 **/
	public static volatile SingularAttribute<OrderHistory, String> newStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderHistory#oldPaymentStatus}
	 **/
	public static volatile SingularAttribute<OrderHistory, String> oldPaymentStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderHistory#newPaymentStatus}
	 **/
	public static volatile SingularAttribute<OrderHistory, String> newPaymentStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderHistory#order}
	 **/
	public static volatile SingularAttribute<OrderHistory, Order> order;

}

