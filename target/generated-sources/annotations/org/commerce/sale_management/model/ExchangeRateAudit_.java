package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.ExchangeRateAudit}
 **/
@StaticMetamodel(ExchangeRateAudit.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class ExchangeRateAudit_ {

	
	/**
	 * @see #id
	 **/
	public static final String ID = "id";
	
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
	 * @see #date
	 **/
	public static final String DATE = "date";
	
	/**
	 * @see #calcAmount
	 **/
	public static final String CALC_AMOUNT = "calcAmount";
	
	/**
	 * @see #exchangeRate
	 **/
	public static final String EXCHANGE_RATE = "exchangeRate";
	
	/**
	 * @see #createdByEmployee
	 **/
	public static final String CREATED_BY_EMPLOYEE = "createdByEmployee";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.ExchangeRateAudit}
	 **/
	public static volatile EntityType<ExchangeRateAudit> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#id}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Long> id;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#currencyFrom}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Currency> currencyFrom;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#currencyTo}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Currency> currencyTo;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#amountFrom}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Double> amountFrom;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#amountTo}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Double> amountTo;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#date}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, LocalDate> date;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#calcAmount}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Double> calcAmount;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#exchangeRate}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, ExchangeRate> exchangeRate;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.ExchangeRateAudit#createdByEmployee}
	 **/
	public static volatile SingularAttribute<ExchangeRateAudit, Employee> createdByEmployee;

}

