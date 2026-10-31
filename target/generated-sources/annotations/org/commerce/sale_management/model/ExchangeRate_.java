package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.ExchangeRate}
 **/
@StaticMetamodel(ExchangeRate.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ExchangeRate_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #currencyFrom
	 **/
	public static final String CURRENCY_FROM = "currencyFrom";
	
	/**
	 * @see #currencyTo
	 **/
	public static final String CURRENCY_TO = "currencyTo";
	
	/**
	 * @see #amount
	 **/
	public static final String AMOUNT = "amount";
	
	/**
	 * @see #currencyDate
	 **/
	public static final String CURRENCY_DATE = "currencyDate";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.ExchangeRate}
	 **/
	public static volatile EntityType<ExchangeRate> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#currencyFrom}
	 **/
	public static volatile SingularAttribute<ExchangeRate, Currency> currencyFrom;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#currencyTo}
	 **/
	public static volatile SingularAttribute<ExchangeRate, Currency> currencyTo;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#amount}
	 **/
	public static volatile SingularAttribute<ExchangeRate, BigDecimal> amount;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#currencyDate}
	 **/
	public static volatile SingularAttribute<ExchangeRate, LocalDate> currencyDate;

}

