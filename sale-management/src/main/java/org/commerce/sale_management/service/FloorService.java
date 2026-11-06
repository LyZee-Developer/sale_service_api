package org.commerce.sale_management.service;

import org.commerce.sale_management.model.Floor;
import org.commerce.sale_management.specification.filter.FloorRequestFilter;
import org.commerce.sale_management.vo.floor.FloorRequest;
import org.module.publish_service.service.BaseInternalService;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 10:48 PM
 */
public interface FloorService extends BaseInternalService<Floor, Long, FloorRequestFilter> {
    Floor save(FloorRequest req);
    Floor update(FloorRequest req);
    String updateStatus(Long id, boolean activate);
}
