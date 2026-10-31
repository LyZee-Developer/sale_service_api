package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.EmployeeIndexEmail}
 **/
@StaticMetamodel(EmployeeIndexEmail.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class EmployeeIndexEmail_ extends BasePrimaryIdEntity_ {

	
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
	 * Static metamodel type for {@link org.commerce.sale_management.model.EmployeeIndexEmail}
	 **/
	public static volatile EntityType<EmployeeIndexEmail> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeIndexEmail#email}
	 **/
	public static volatile SingularAttribute<EmployeeIndexEmail, String> email;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeIndexEmail#facebook}
	 **/
	public static volatile SingularAttribute<EmployeeIndexEmail, String> facebook;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeIndexEmail#phone}
	 **/
	public static volatile SingularAttribute<EmployeeIndexEmail, String> phone;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.EmployeeIndexEmail#phone1}
	 **/
	public static volatile SingularAttribute<EmployeeIndexEmail, String> phone1;

}

