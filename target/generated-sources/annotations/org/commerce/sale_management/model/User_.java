package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.User}
 **/
@StaticMetamodel(User.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class User_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #username
	 **/
	public static final String USERNAME = "username";
	
	/**
	 * @see #passwordHash
	 **/
	public static final String PASSWORD_HASH = "passwordHash";
	
	/**
	 * @see #attempt
	 **/
	public static final String ATTEMPT = "attempt";
	
	/**
	 * @see #employee
	 **/
	public static final String EMPLOYEE = "employee";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.User}
	 **/
	public static volatile EntityType<User> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.User#username}
	 **/
	public static volatile SingularAttribute<User, String> username;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.User#passwordHash}
	 **/
	public static volatile SingularAttribute<User, String> passwordHash;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.User#attempt}
	 **/
	public static volatile SingularAttribute<User, Integer> attempt;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.User#employee}
	 **/
	public static volatile SingularAttribute<User, Employee> employee;

}

