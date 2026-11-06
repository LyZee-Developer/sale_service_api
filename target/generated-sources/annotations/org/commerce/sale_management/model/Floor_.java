package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.Floor}
 **/
@StaticMetamodel(Floor.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Floor_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #name
	 **/
	public static final String NAME = "name";
	
	/**
	 * @see #nameEn
	 **/
	public static final String NAME_EN = "nameEn";
	
	/**
	 * @see #descriptionEn
	 **/
	public static final String DESCRIPTION_EN = "descriptionEn";
	
	/**
	 * @see #description
	 **/
	public static final String DESCRIPTION = "description";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.Floor}
	 **/
	public static volatile EntityType<Floor> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Floor#name}
	 **/
	public static volatile SingularAttribute<Floor, String> name;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Floor#nameEn}
	 **/
	public static volatile SingularAttribute<Floor, String> nameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Floor#descriptionEn}
	 **/
	public static volatile SingularAttribute<Floor, String> descriptionEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Floor#description}
	 **/
	public static volatile SingularAttribute<Floor, String> description;

}

