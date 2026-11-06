package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
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
	 * @see #amountFrom
	 **/
	public static final String AMOUNT_FROM = "amountFrom";
	
	/**
	 * @see #amountTo
	 **/
	public static final String AMOUNT_TO = "amountTo";
	
	/**
	 * @see #calcAmount
	 **/
	public static final String CALC_AMOUNT = "calcAmount";
	
	/**
	 * @see #date
	 **/
	public static final String DATE = "date";
	
	/**
	 * @see #exchangeRateAudits
	 **/
	public static final String EXCHANGE_RATE_AUDITS = "exchangeRateAudits";
	
	/**
	 * @see #createdByEmployee
	 **/
	public static final String CREATED_BY_EMPLOYEE = "createdByEmployee";

	
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
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#amountFrom}
	 **/
	public static volatile SingularAttribute<ExchangeRate, Double> amountFrom;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#amountTo}
	 **/
	public static volatile SingularAttribute<ExchangeRate, Double> amountTo;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#calcAmount}
	 **/
	public static volatile SingularAttribute<ExchangeRate, Double> calcAmount;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#date}
	 **/
	public static volatile SingularAttribute<ExchangeRate, LocalDate> date;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#exchangeRateAudits}
	 **/
	public static volatile ListAttribute<ExchangeRate, ExchangeRateAudit> exchangeRateAudits;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRate#createdByEmployee}
	 **/
	public static volatile SingularAttribute<ExchangeRate, Employee> createdByEmployee;

}

