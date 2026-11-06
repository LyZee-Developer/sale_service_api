package org.commerce.sale_management.service.impl.floor;

import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.Floor;
import org.commerce.sale_management.repository.FloorRepository;
import org.commerce.sale_management.service.FloorService;
import org.commerce.sale_management.specification.FloorSpecification;
import org.commerce.sale_management.specification.filter.FloorRequestFilter;
import org.commerce.sale_management.vo.floor.FloorRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:48 PM
 */
@Service
public class FloorServiceImpl extends BaseInternalServiceImpl<Floor, Long, FloorRepository, FloorRequestFilter, FloorSpecification> implements FloorService {

    @Override
    public Floor save(FloorRequest req) {
        validExistedName(req.getName());
        return save(mapData(req));
    }

    @Override
    public Floor update(FloorRequest req) {
        this.getOneOrThrow(req.getId());
        return update(mapData(req));
    }

    private void validExistedName(String name) {
        boolean isExist = repository.existsByNameAndIsActivateTrue(name);
        if (isExist) {
            throw new ApiResponseHandler("Floor name [%s] is already set.".formatted(name));
        }
    }

    private Floor mapData(FloorRequest req) {
        Floor floor = Optional.ofNullable(req.getId()).map(this::getOneOrThrow).orElseGet(Floor::new);
        floor.setName(req.getName());
        floor.setNameEn(req.getNameEn());
        floor.setDescriptionEn(req.getDescriptionEn());
        floor.setDescription(req.getDescription());
        return floor;
    }

    @Override
    public String updateStatus(Long id, boolean activate) {
        Floor floor = this.getOneOrThrow(id);
        floor.setIsActivate(activate);
        update(floor);
        return "Floor have been %s successfully.".formatted(activate ? "enabled" : "disabled");
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
