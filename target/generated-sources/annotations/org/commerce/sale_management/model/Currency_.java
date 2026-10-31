package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.Currency}
 **/
@StaticMetamodel(Currency.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Currency_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #nameKh
	 **/
	public static final String NAME_KH = "nameKh";
	
	/**
	 * @see #symbol
	 **/
	public static final String SYMBOL = "symbol";
	
	/**
	 * @see #code
	 **/
	public static final String CODE = "code";
	
	/**
	 * @see #decimalPlaces
	 **/
	public static final String DECIMAL_PLACES = "decimalPlaces";
	
	/**
	 * @see #nameEn
	 **/
	public static final String NAME_EN = "nameEn";
	
	/**
	 * @see #isBase
	 **/
	public static final String IS_BASE = "isBase";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.Currency}
	 **/
	public static volatile EntityType<Currency> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#nameKh}
	 **/
	public static volatile SingularAttribute<Currency, String> nameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#symbol}
	 **/
	public static volatile SingularAttribute<Currency, String> symbol;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#code}
	 **/
	public static volatile SingularAttribute<Currency, String> code;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#decimalPlaces}
	 **/
	public static volatile SingularAttribute<Currency, Double> decimalPlaces;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#nameEn}
	 **/
	public static volatile SingularAttribute<Currency, String> nameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#isBase}
	 **/
	public static volatile SingularAttribute<Currency, Boolean> isBase;

}

