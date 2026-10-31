package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.EmployeeEmail}
 **/
@StaticMetamodel(EmployeeEmail.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class EmployeeEmail_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #email
	 **/
	public static final String EMAIL = "email";
	
	/**
	 * @see #facebook
	 **/
	public static final String FACEBOOK = "facebook";
	
	/**
	 * @see #phone
	 **/
	public static final String PHONE = "phone";
	
	/**
	 * @see #phone1
	 **/
	public static final String PHONE1 = "phone1";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.EmployeeEmail}
	 **/
	public static volatile EntityType<EmployeeEmail> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeEmail#email}
	 **/
	public static volatile SingularAttribute<EmployeeEmail, String> email;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeEmail#facebook}
	 **/
	public static volatile SingularAttribute<EmployeeEmail, String> facebook;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeEmail#phone}
	 **/
	public static volatile SingularAttribute<EmployeeEmail, String> phone;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeEmail#phone1}
	 **/
	public static volatile SingularAttribute<EmployeeEmail, String> phone1;

}

