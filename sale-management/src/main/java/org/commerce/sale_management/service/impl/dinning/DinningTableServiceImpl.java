package org.commerce.sale_management.service.impl.dinning;

import jakarta.persistence.Table;
import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.Floor;
import org.commerce.sale_management.repository.DinningTableRepository;
import org.commerce.sale_management.service.DinningTableService;
import org.commerce.sale_management.service.FloorService;
import org.commerce.sale_management.specification.DinningTableSpecification;
import org.commerce.sale_management.specification.filter.DinningTableRequestFilter;
import org.commerce.sale_management.vo.dinning.DinningTableRequest;
import org.module.publish_service.constant.App;
import org.module.publish_service.exception.ApiResponseHandler;
import org.module.publish_service.service.impl.BaseInternalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:48 PM
 */
@Service
public class DinningTableServiceImpl extends BaseInternalServiceImpl<DinningTable, Long, DinningTableRepository, DinningTableRequestFilter, DinningTableSpecification> implements DinningTableService {

    @Autowired
    private FloorService floorService;

    @Override
    public DinningTable save(DinningTableRequest.create req) {
        validExistedTableName(req.getTableName(), null);
        return save(mapData(req));
    }

    private void validExistedTableName(String tableName, Long id) {
        repository.findByTableNameAndIsActivateTrue(tableName).ifPresent((table) -> {
            if (id != null && !Objects.equals(table.getId(), id)) {
                throw new ApiResponseHandler("Table name [%s] is already set.".formatted(tableName));
            }
        });
    }

    private DinningTable mapData(DinningTableRequest.create req) {
        Floor floor = floorService.getOne(req.getFloorId()).orElse(null);

        return DinningTable.builder()
            .tableName(req.getTableName())
            .description(req.getDescription())
            .capacity(req.getCapacity())
            .floor(floor)
            .build();
    }

    @Override
    public String updateStatus(Long id, boolean activate) {
        DinningTable table = this.getOneOrThrow(id);
        table.setIsActivate(activate);
        update(table);
        return "Table have been %s successfully.".formatted(activate ? "enabled" : "disabled");
    }

    @Override
    public List<DinningTableDTO> availableTable(List<Long> useTableIds) {
        List<DinningTable> tables = (useTableIds == null || useTableIds.isEmpty())
            ? repository.findAllByIsActivateTrue()
            : repository.getTableAvailable(useTableIds);
        if (tables.isEmpty()) {
            return Collections.emptyList();
        }
        return tables.stream().map(DinningTableDTO::new).toList();
    }

    @Override
    public DinningTable update(DinningTableRequest.update req) {
        Long id = req.getId();
        validExistedTableName(req.getTableName(), id);
        Floor floor = floorService.getOne(req.getFloorId()).orElse(null);

        DinningTable table = this.getOneOrThrow(id);
        table.setTableName(req.getTableName());
        table.setCapacity(req.getCapacity());
        table.setDescription(req.getDescription());
        table.setFloor(floor);
        update(table);
        return table;
    }

    @Override
    public String currentUser() {
        return App.USER;
    }
}
