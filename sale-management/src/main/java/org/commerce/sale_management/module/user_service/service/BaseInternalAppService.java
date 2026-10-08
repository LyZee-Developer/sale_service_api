package org.commerce.sale_management.module.user_service.service;

import org.module.publish_service.constant.App;
import org.module.publish_service.model.BaseEntity;
import org.module.publish_service.request.BaseInternalRequestFilter;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.module.publish_service.specification.BaseCombineRepositorySpecification;
import org.module.publish_service.specification.BaseInternalSpecification;

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

    @Override
    public String currentUser() {
        return App.USER;
    }
}
