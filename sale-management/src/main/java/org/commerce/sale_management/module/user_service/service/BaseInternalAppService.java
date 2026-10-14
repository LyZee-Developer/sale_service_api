package org.commerce.sale_management.module.user_service.service;

import org.module.publish_service.constant.App;
import org.module.publish_service.model.BaseEntity;
import org.module.publish_service.request.BaseInternalRequestFilter;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.module.publish_service.specification.BaseInternalSpecification;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.Serializable;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/8/2026 8:06 PM
 */
public class BaseInternalAppService<ENTITY extends BaseEntity,
        ENTITY_TYPE extends Serializable,
        REPO extends BaseCombineRepositorySpecification<ENTITY, ENTITY_TYPE>,
        FILTER extends BaseInternalRequestFilter,
        SPECI extends BaseInternalSpecification<ENTITY, FILTER>> extends BaseInternalServiceImpl<ENTITY, ENTITY_TYPE, REPO, FILTER, SPECI> {

    /**
     * Username of the authenticated caller, used for createdBy/modifiedBy.
     * Falls back to App.USER for work done outside a request (e.g. startup bootstrap).
     */
    @Override
    public String currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return App.USER;
        }
        return auth.getName();
    }
}
