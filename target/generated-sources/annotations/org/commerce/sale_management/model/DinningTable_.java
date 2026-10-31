package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.DinningTable}
 **/
@StaticMetamodel(DinningTable.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class DinningTable_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #tableName
	 **/
	public static final String TABLE_NAME = "tableName";
	
	/**
	 * @see #capacity
	 **/
	public static final String CAPACITY = "capacity";
	
	/**
	 * @see #description
	 **/
	public static final String DESCRIPTION = "description";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.DinningTable}
	 **/
	public static volatile EntityType<DinningTable> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DinningTable#tableName}
	 **/
	public static volatile SingularAttribute<DinningTable, String> tableName;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DinningTable#capacity}
	 **/
	public static volatile SingularAttribute<DinningTable, Integer> capacity;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DinningTable#description}
	 **/
	public static volatile SingularAttribute<DinningTable, String> description;

}

