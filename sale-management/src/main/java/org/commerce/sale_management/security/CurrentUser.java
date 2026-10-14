package org.commerce.sale_management.security;

import org.commerce.sale_management.module.user_service.model.User;
import org.commerce.sale_management.module.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

/**
 * The authenticated caller, for user foreign keys such as orders.created_by_user_id,
 * payments.received_by and status-history changed_by.
 *
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/14/2026 4:30 PM
 */
@Component
public class CurrentUser {

    @Autowired
    private UserRepository userRepository;

    public Long id() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof Jwt jwt) {
            Object uid = jwt.getClaim(JwtTokenService.CLAIM_USER_ID);
            if (uid instanceof Number number) {
                return number.longValue();
            }
        }
        throw new AuthenticationCredentialsNotFoundException("No authenticated user");
    }

    /** Lazy reference (no SELECT) suitable for setting a @ManyToOne User field. */
    public User reference() {
        return userRepository.getReferenceById(id());
    }
}
