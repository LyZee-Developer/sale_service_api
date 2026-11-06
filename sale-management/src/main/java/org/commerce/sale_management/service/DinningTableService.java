package org.commerce.sale_management.service;

import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.specification.filter.DinningTableRequestFilter;
import org.commerce.sale_management.vo.dinning.DinningTableRequest;
import org.module.publish_service.service.BaseInternalService;

import java.util.List;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:43 PM
 */
public interface DinningTableService extends BaseInternalService<DinningTable, Long, DinningTableRequestFilter> {
    DinningTable save(DinningTableRequest.create req);

    DinningTable update(DinningTableRequest.update req);

    String updateStatus(Long id, boolean activate);

    List<DinningTableDTO> availableTable(List<Long> useTableIds);
}
