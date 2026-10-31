package org.commerce.sale_management.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/29/2026 12:00 AM
 */
@Component
public class PasswordUtil {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encryptPassword(String pw) {
        return passwordEncoder.encode(pw);
    }

    public boolean isMatch(String value, String encryptPassword) {
        return passwordEncoder.matches(value, encryptPassword);
    }
}
