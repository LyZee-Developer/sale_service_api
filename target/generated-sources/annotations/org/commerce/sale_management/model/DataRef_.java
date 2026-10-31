package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.DataRef}
 **/
@StaticMetamodel(DataRef.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class DataRef_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #code
	 **/
	public static final String CODE = "code";
	
	/**
	 * @see #nameKh
	 **/
	public static final String NAME_KH = "nameKh";
	
	/**
	 * @see #nameEn
	 **/
	public static final String NAME_EN = "nameEn";
	
	/**
	 * @see #descriptionKh
	 **/
	public static final String DESCRIPTION_KH = "descriptionKh";
	
	/**
	 * @see #descriptionEn
	 **/
	public static final String DESCRIPTION_EN = "descriptionEn";
	
	/**
	 * @see #value
	 **/
	public static final String VALUE = "value";
	
	/**
	 * @see #level
	 **/
	public static final String LEVEL = "level";
	
	/**
	 * @see #orderNumber
	 **/
	public static final String ORDER_NUMBER = "orderNumber";
	
	/**
	 * @see #parent
	 **/
	public static final String PARENT = "parent";
	
	/**
	 * @see #subDataRef
	 **/
	public static final String SUB_DATA_REF = "subDataRef";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.DataRef}
	 **/
	public static volatile EntityType<DataRef> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#code}
	 **/
	public static volatile SingularAttribute<DataRef, String> code;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#nameKh}
	 **/
	public static volatile SingularAttribute<DataRef, String> nameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#nameEn}
	 **/
	public static volatile SingularAttribute<DataRef, String> nameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#descriptionKh}
	 **/
	public static volatile SingularAttribute<DataRef, String> descriptionKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#descriptionEn}
	 **/
	public static volatile SingularAttribute<DataRef, String> descriptionEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#value}
	 **/
	public static volatile SingularAttribute<DataRef, String> value;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#level}
	 **/
	public static volatile SingularAttribute<DataRef, Integer> level;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#orderNumber}
	 **/
	public static volatile SingularAttribute<DataRef, Integer> orderNumber;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#parent}
	 **/
	public static volatile SingularAttribute<DataRef, DataRef> parent;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.DataRef#subDataRef}
	 **/
	public static volatile ListAttribute<DataRef, DataRef> subDataRef;

}

