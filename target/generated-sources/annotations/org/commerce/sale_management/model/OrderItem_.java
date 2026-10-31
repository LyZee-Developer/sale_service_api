package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.OrderItem}
 **/
@StaticMetamodel(OrderItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class OrderItem_ {

	
	/**
	 * @see #id
	 **/
	public static final String ID = "id";
	
	/**
	 * @see #item
	 **/
	public static final String ITEM = "item";
	
	/**
	 * @see #order
	 **/
	public static final String ORDER = "order";
	
	/**
	 * @see #isCountIn
	 **/
	public static final String IS_COUNT_IN = "isCountIn";
	
	/**
	 * @see #qty
	 **/
	public static final String QTY = "qty";
	
	/**
	 * @see #unitPrice
	 **/
	public static final String UNIT_PRICE = "unitPrice";
	
	/**
	 * @see #specialInstructions
	 **/
	public static final String SPECIAL_INSTRUCTIONS = "specialInstructions";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.OrderItem}
	 **/
	public static volatile EntityType<OrderItem> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#id}
	 **/
	public static volatile SingularAttribute<OrderItem, Long> id;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#item}
	 **/
	public static volatile SingularAttribute<OrderItem, MenuItem> item;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#order}
	 **/
	public static volatile SingularAttribute<OrderItem, Order> order;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#isCountIn}
	 **/
	public static volatile SingularAttribute<OrderItem, Boolean> isCountIn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#qty}
	 **/
	public static volatile SingularAttribute<OrderItem, Integer> qty;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#unitPrice}
	 **/
	public static volatile SingularAttribute<OrderItem, BigDecimal> unitPrice;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.OrderItem#specialInstructions}
	 **/
	public static volatile SingularAttribute<OrderItem, String> specialInstructions;

}

