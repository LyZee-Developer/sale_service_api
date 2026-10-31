package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.MenuCategory}
 **/
@StaticMetamodel(MenuCategory.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class MenuCategory_ extends BasePrimaryIdEntity_ {

	
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
	 * @see #items
	 **/
	public static final String ITEMS = "items";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.MenuCategory}
	 **/
	public static volatile EntityType<MenuCategory> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuCategory#nameKh}
	 **/
	public static volatile SingularAttribute<MenuCategory, String> nameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuCategory#nameEn}
	 **/
	public static volatile SingularAttribute<MenuCategory, String> nameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuCategory#descriptionKh}
	 **/
	public static volatile SingularAttribute<MenuCategory, String> descriptionKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuCategory#descriptionEn}
	 **/
	public static volatile SingularAttribute<MenuCategory, String> descriptionEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.MenuCategory#items}
	 **/
	public static volatile ListAttribute<MenuCategory, MenuItem> items;

}

