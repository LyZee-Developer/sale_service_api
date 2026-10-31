package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.MenuItem}
 **/
@StaticMetamodel(MenuItem.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class MenuItem_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #nameKh
	 **/
	public static final String NAME_KH = "nameKh";
	
	/**
	 * @see #nameEn
	 **/
	public static final String NAME_EN = "nameEn";
	
	/**
	 * @see #isAvailable
	 **/
	public static final String IS_AVAILABLE = "isAvailable";
	
	/**
	 * @see #price
	 **/
	public static final String PRICE = "price";
	
	/**
	 * @see #discount
	 **/
	public static final String DISCOUNT = "discount";
	
	/**
	 * @see #descriptionEn
	 **/
	public static final String DESCRIPTION_EN = "descriptionEn";
	
	/**
	 * @see #descriptionKh
	 **/
	public static final String DESCRIPTION_KH = "descriptionKh";
	
	/**
	 * @see #category
	 **/
	public static final String CATEGORY = "category";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.MenuItem}
	 **/
	public static volatile EntityType<MenuItem> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#nameKh}
	 **/
	public static volatile SingularAttribute<MenuItem, String> nameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#nameEn}
	 **/
	public static volatile SingularAttribute<MenuItem, String> nameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#isAvailable}
	 **/
	public static volatile SingularAttribute<MenuItem, Boolean> isAvailable;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#price}
	 **/
	public static volatile SingularAttribute<MenuItem, BigDecimal> price;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#discount}
	 **/
	public static volatile SingularAttribute<MenuItem, Double> discount;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#descriptionEn}
	 **/
	public static volatile SingularAttribute<MenuItem, String> descriptionEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#descriptionKh}
	 **/
	public static volatile SingularAttribute<MenuItem, String> descriptionKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuItem#category}
	 **/
	public static volatile SingularAttribute<MenuItem, MenuCategory> category;

}

