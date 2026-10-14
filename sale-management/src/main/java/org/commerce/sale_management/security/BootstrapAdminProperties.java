package org.commerce.sale_management.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
@ConfigurationProperties(prefix = "app.bootstrap-admin")
public record BootstrapAdminProperties(String username, String password) {
}
