package org.commerce.sale_management.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import org.commerce.sale_management.constant.EmployeeConst.Status;
import org.module.publish_service.model.BasePrimaryIdEntity_;

/**
 * Static metamodel for {@link org.commerce.sale_management.model.Employee}
 **/
@StaticMetamodel(Employee.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Employee_ extends BasePrimaryIdEntity_ {

	
	/**
	 * @see #code
	 **/
	public static final String CODE = "code";
	
	/**
	 * @see #firstNameKh
	 **/
	public static final String FIRST_NAME_KH = "firstNameKh";
	
	/**
	 * @see #lastNameKh
	 **/
	public static final String LAST_NAME_KH = "lastNameKh";
	
	/**
	 * @see #firstNameEn
	 **/
	public static final String FIRST_NAME_EN = "firstNameEn";
	
	/**
	 * @see #lastNameEn
	 **/
	public static final String LAST_NAME_EN = "lastNameEn";
	
	/**
	 * @see #email
	 **/
	public static final String EMAIL = "email";
	
	/**
	 * @see #phone
	 **/
	public static final String PHONE = "phone";
	
	/**
	 * @see #phone1
	 **/
	public static final String PHONE1 = "phone1";
	
	/**
	 * @see #gender
	 **/
	public static final String GENDER = "gender";
	
	/**
	 * @see #dob
	 **/
	public static final String DOB = "dob";
	
	/**
	 * @see #hireDate
	 **/
	public static final String HIRE_DATE = "hireDate";
	
	/**
	 * @see #address
	 **/
	public static final String ADDRESS = "address";
	
	/**
	 * @see #nId
	 **/
	public static final String N_ID = "nId";
	
	/**
	 * @see #salary
	 **/
	public static final String SALARY = "salary";
	
	/**
	 * @see #status
	 **/
	public static final String STATUS = "status";
	
	/**
	 * @see #departmentId
	 **/
	public static final String DEPARTMENT_ID = "departmentId";
	
	/**
	 * @see #positionId
	 **/
	public static final String POSITION_ID = "positionId";
	
	/**
	 * @see #lineManager
	 **/
	public static final String LINE_MANAGER = "lineManager";
	
	/**
	 * @see #employees
	 **/
	public static final String EMPLOYEES = "employees";

	
	/**
	 * Static metamodel type for {@link org.commerce.sale_management.model.Employee}
	 **/
	public static volatile EntityType<Employee> class_;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#code}
	 **/
	public static volatile SingularAttribute<Employee, String> code;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#firstNameKh}
	 **/
	public static volatile SingularAttribute<Employee, String> firstNameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#lastNameKh}
	 **/
	public static volatile SingularAttribute<Employee, String> lastNameKh;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#firstNameEn}
	 **/
	public static volatile SingularAttribute<Employee, String> firstNameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#lastNameEn}
	 **/
	public static volatile SingularAttribute<Employee, String> lastNameEn;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#email}
	 **/
	public static volatile SingularAttribute<Employee, String> email;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#phone}
	 **/
	public static volatile SingularAttribute<Employee, String> phone;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#phone1}
	 **/
	public static volatile SingularAttribute<Employee, String> phone1;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#gender}
	 **/
	public static volatile SingularAttribute<Employee, Boolean> gender;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#dob}
	 **/
	public static volatile SingularAttribute<Employee, LocalDate> dob;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#hireDate}
	 **/
	public static volatile SingularAttribute<Employee, LocalDate> hireDate;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#address}
	 **/
	public static volatile SingularAttribute<Employee, String> address;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#nId}
	 **/
	public static volatile SingularAttribute<Employee, String> nId;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#salary}
	 **/
	public static volatile SingularAttribute<Employee, String> salary;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#status}
	 **/
	public static volatile SingularAttribute<Employee, Status> status;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#departmentId}
	 **/
	public static volatile SingularAttribute<Employee, Long> departmentId;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#positionId}
	 **/
	public static volatile SingularAttribute<Employee, Long> positionId;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#lineManager}
	 **/
	public static volatile SingularAttribute<Employee, Employee> lineManager;
	
	/**
	 * Static metamodel for attribute {@link org.commerce.sale_management.model.Employee#employees}
	 **/
	public static volatile ListAttribute<Employee, Employee> employees;

}

