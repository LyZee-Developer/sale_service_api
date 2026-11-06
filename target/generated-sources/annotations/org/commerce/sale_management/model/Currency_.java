package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.Currency}
 **/
@StaticMetamodel(Currency.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Currency_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #name
	 **/
	public static final String NAME = "name";
	
	/**
	 * @see #nameEn
	 **/
	public static final String NAME_EN = "nameEn";
	
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
	 * @see #isBase
	 **/
	public static final String IS_BASE = "isBase";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.Currency}
	 **/
	public static volatile EntityType<Currency> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#name}
	 **/
	public static volatile SingularAttribute<Currency, String> name;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#nameEn}
	 **/
	public static volatile SingularAttribute<Currency, String> nameEn;
	
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
	public static volatile SingularAttribute<Currency, BigDecimal> decimalPlaces;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Currency#isBase}
	 **/
	public static volatile SingularAttribute<Currency, Boolean> isBase;

}

