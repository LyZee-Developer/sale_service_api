package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import org.commerce.sale_management.constant.KitchenTicketConst.Status;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.KitchenTicket}
 **/
@StaticMetamodel(KitchenTicket.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class KitchenTicket_ {

	
	/**
	 * @see #id
	 **/
	public static final String ID = "id";
	
	/**
	 * @see #tickerNumber
	 **/
	public static final String TICKER_NUMBER = "tickerNumber";
	
	/**
	 * @see #order
	 **/
	public static final String ORDER = "order";
	
	/**
	 * @see #table
	 **/
	public static final String TABLE = "table";
	
	/**
	 * @see #sentAt
	 **/
	public static final String SENT_AT = "sentAt";
	
	/**
	 * @see #printedAt
	 **/
	public static final String PRINTED_AT = "printedAt";
	
	/**
	 * @see #employee
	 **/
	public static final String EMPLOYEE = "employee";
	
	/**
	 * @see #status
	 **/
	public static final String STATUS = "status";
	
	/**
	 * @see #kitchenTicketsHistories
	 **/
	public static final String KITCHEN_TICKETS_HISTORIES = "kitchenTicketsHistories";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.KitchenTicket}
	 **/
	public static volatile EntityType<KitchenTicket> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#id}
	 **/
	public static volatile SingularAttribute<KitchenTicket, Long> id;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#tickerNumber}
	 **/
	public static volatile SingularAttribute<KitchenTicket, String> tickerNumber;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#order}
	 **/
	public static volatile SingularAttribute<KitchenTicket, Order> order;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#table}
	 **/
	public static volatile SingularAttribute<KitchenTicket, DinningTable> table;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#sentAt}
	 **/
	public static volatile SingularAttribute<KitchenTicket, LocalDateTime> sentAt;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#printedAt}
	 **/
	public static volatile SingularAttribute<KitchenTicket, LocalDateTime> printedAt;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#employee}
	 **/
	public static volatile SingularAttribute<KitchenTicket, Employee> employee;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#status}
	 **/
	public static volatile SingularAttribute<KitchenTicket, Status> status;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicket#kitchenTicketsHistories}
	 **/
	public static volatile ListAttribute<KitchenTicket, KitchenTicketHistory> kitchenTicketsHistories;

}

