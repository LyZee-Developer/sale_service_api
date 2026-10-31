package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.UserAddress}
 **/
@StaticMetamodel(UserAddress.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class UserAddress_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #facebook
	 **/
	public static final String FACEBOOK = "facebook";
	
	/**
	 * @see #email
	 **/
	public static final String EMAIL = "email";
	
	/**
	 * @see #phone
	 **/
	public static final String PHONE = "phone";
	
	/**
	 * @see #phone1
	 **/
	public static final String PHONE1 = "phone1";
	
	/**
	 * @see #address
	 **/
	public static final String ADDRESS = "address";
	
	/**
	 * @see #user
	 **/
	public static final String USER = "user";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.UserAddress}
	 **/
	public static volatile EntityType<UserAddress> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserAddress#facebook}
	 **/
	public static volatile SingularAttribute<UserAddress, String> facebook;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserAddress#email}
	 **/
	public static volatile SingularAttribute<UserAddress, String> email;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserAddress#phone}
	 **/
	public static volatile SingularAttribute<UserAddress, String> phone;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserAddress#phone1}
	 **/
	public static volatile SingularAttribute<UserAddress, String> phone1;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserAddress#address}
	 **/
	public static volatile SingularAttribute<UserAddress, String> address;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.UserAddress#user}
	 **/
	public static volatile SingularAttribute<UserAddress, UserTesting> user;

}

