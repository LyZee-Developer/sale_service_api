package org.commerce.sale_management.module.user_service.service.impl;

import org.commerce.sale_management.module.user_service.service.BaseInternalService;
import org.commerce.sale_management.module.user_service.specification.BaseInternalSpecification;
import org.commerce.sale_management.module.user_service.specification.filter.UserRequestFilter;
import org.module.publish_service.exception.ResourceNotFoundException;
import org.module.publish_service.model.BaseEntity;
import org.module.publish_service.request.BaseInternalRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/4/2026 2:36 PM
 */
@Service
public class BaseInternalServiceImpl<ENT extends BaseEntity,
        SRI extends Serializable,
        REPO extends JpaRepository<ENT, SRI>,
        FILTER extends BaseInternalRequestFilter,
        SPECI extends BaseInternalSpecification<ENT, UserRequestFilter>>
        implements BaseInternalService<ENT> {

    @Autowired
    protected REPO repository;

    private ENT entity;

    private FILTER filter;

    private SPECI specification;

    public ENT save(ENT entity) {
        return this.repository.save(entity);
    }

    public ENT findOne(SRI id) {
        return Optional.ofNullable(id)
                .flatMap(this.repository::findById)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found for ID: " + id));
    }

    public ENT findOneThrow(SRI id) {
        if (id == null) {
            throw new ResourceNotFoundException("ID cannot be null");
        }
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found with id: " + id));
    }

}
