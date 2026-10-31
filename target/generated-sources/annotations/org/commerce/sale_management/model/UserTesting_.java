package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.UserTesting}
 **/
@StaticMetamodel(UserTesting.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class UserTesting_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #username
	 **/
	public static final String USERNAME = "username";
	
	/**
	 * @see #password
	 **/
	public static final String PASSWORD = "password";
	
	/**
	 * @see #userAddressList
	 **/
	public static final String USER_ADDRESS_LIST = "userAddressList";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.UserTesting}
	 **/
	public static volatile EntityType<UserTesting> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserTesting#username}
	 **/
	public static volatile SingularAttribute<UserTesting, String> username;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserTesting#password}
	 **/
	public static volatile SingularAttribute<UserTesting, String> password;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserTesting#userAddressList}
	 **/
	public static volatile ListAttribute<UserTesting, UserAddress> userAddressList;

}

