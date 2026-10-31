package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.commerce.sale_management.constant.CustomerConst.Type;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.Customer}
 **/
@StaticMetamodel(Customer.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Customer_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #code
	 **/
	public static final String CODE = "code";
	
	/**
	 * @see #fullNameKh
	 **/
	public static final String FULL_NAME_KH = "fullNameKh";
	
	/**
	 * @see #fullNameEn
	 **/
	public static final String FULL_NAME_EN = "fullNameEn";
	
	/**
	 * @see #phone
	 **/
	public static final String PHONE = "phone";
	
	/**
	 * @see #phone1
	 **/
	public static final String PHONE1 = "phone1";
	
	/**
	 * @see #gender
	 **/
	public static final String GENDER = "gender";
	
	/**
	 * @see #email
	 **/
	public static final String EMAIL = "email";
	
	/**
	 * @see #type
	 **/
	public static final String TYPE = "type";
	
	/**
	 * @see #employee
	 **/
	public static final String EMPLOYEE = "employee";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.Customer}
	 **/
	public static volatile EntityType<Customer> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#code}
	 **/
	public static volatile SingularAttribute<Customer, String> code;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#fullNameKh}
	 **/
	public static volatile SingularAttribute<Customer, String> fullNameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#fullNameEn}
	 **/
	public static volatile SingularAttribute<Customer, String> fullNameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#phone}
	 **/
	public static volatile SingularAttribute<Customer, String> phone;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#phone1}
	 **/
	public static volatile SingularAttribute<Customer, String> phone1;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#gender}
	 **/
	public static volatile SingularAttribute<Customer, Boolean> gender;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#email}
	 **/
	public static volatile SingularAttribute<Customer, Boolean> email;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#type}
	 **/
	public static volatile SingularAttribute<Customer, Type> type;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Customer#employee}
	 **/
	public static volatile SingularAttribute<Customer, Employee> employee;

}

