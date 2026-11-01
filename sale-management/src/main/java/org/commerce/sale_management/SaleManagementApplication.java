package org.commerce.sale_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"org.commerce.sale_management", "org.module.publish_service"})
@EnableCaching
@EnableScheduling
public class SaleManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaleManagementApplication.class, args);
    }

}
