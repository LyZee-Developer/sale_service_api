package org.commerce.sale_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"org.commerce.sale_management",
				"org.module.publish_service"
		}
)
public class SaleManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaleManagementApplication.class, args);
	}

}
