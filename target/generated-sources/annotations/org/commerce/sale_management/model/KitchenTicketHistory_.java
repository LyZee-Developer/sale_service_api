package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.KitchenTicketHistory}
 **/
@StaticMetamodel(KitchenTicketHistory.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class KitchenTicketHistory_ {

	
	/**
	 * @see #id
	 **/
	public static final String ID = "id";
	
	/**
	 * @see #oldStatus
	 **/
	public static final String OLD_STATUS = "oldStatus";
	
	/**
	 * @see #newStatus
	 **/
	public static final String NEW_STATUS = "newStatus";
	
	/**
	 * @see #kitchenTicket
	 **/
	public static final String KITCHEN_TICKET = "kitchenTicket";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.KitchenTicketHistory}
	 **/
	public static volatile EntityType<KitchenTicketHistory> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicketHistory#id}
	 **/
	public static volatile SingularAttribute<KitchenTicketHistory, Long> id;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicketHistory#oldStatus}
	 **/
	public static volatile SingularAttribute<KitchenTicketHistory, String> oldStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicketHistory#newStatus}
	 **/
	public static volatile SingularAttribute<KitchenTicketHistory, String> newStatus;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.KitchenTicketHistory#kitchenTicket}
	 **/
	public static volatile SingularAttribute<KitchenTicketHistory, KitchenTicket> kitchenTicket;

}

