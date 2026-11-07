package org.commerce.sale_management.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.commerce.sale_management.constant.ApiUrlConstant;
import org.commerce.sale_management.dto.DinningTableDTO;
import org.commerce.sale_management.dto.FloorDTO;
import org.commerce.sale_management.model.DinningTable;
import org.commerce.sale_management.model.Floor;
import org.commerce.sale_management.service.DinningTableService;
import org.commerce.sale_management.service.FloorService;
import org.commerce.sale_management.service.JDBCTemplateService;
import org.commerce.sale_management.specification.filter.DinningTableRequestFilter;
import org.commerce.sale_management.specification.filter.FloorRequestFilter;
import org.commerce.sale_management.view.FloorView;
import org.commerce.sale_management.vo.dinning.DinningTableRequest;
import org.commerce.sale_management.vo.floor.FloorRequest;
import org.module.publish_service.model.BaseEntity_;
import org.module.publish_service.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:31 PM
 */
@RestController
@RequestMapping(ApiUrlConstant.FLOOR)
public class FloorController {
    @Autowired
    private FloorService floorService;

    @Autowired
    private ApiResponse apiResponse;

    @Autowired
    private JDBCTemplateService jdbcTemplateService;

    @GetMapping
    public ResponseEntity<Object> index(@ModelAttribute FloorRequestFilter filter,
                                        @PageableDefault(sort = BaseEntity_.CREATED_AT, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(apiResponse.success(floorService.getAllOrView(filter, pageable, FloorDTO.class)));
    }

    @GetMapping("/one/{id}")
    public ResponseEntity<Object> getOne(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(apiResponse.success(floorService.getOneOrView(id, FloorDTO.class)));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody FloorRequest req) {
        Floor floor = floorService.save(req);
        return ResponseEntity.ok(apiResponse.success(new FloorDTO(floor)));
    }

    @PutMapping
    public ResponseEntity<Object> update(@Valid @RequestBody FloorRequest req) {
        Floor floor = floorService.update(req);
        return ResponseEntity.ok(apiResponse.success(new FloorDTO(floor)));
    }

    @GetMapping("/test")
    public ResponseEntity<Object> test() {
        List<FloorView> result = jdbcTemplateService.executeScript(
            "SELECT name, name_en as nameEnglish FROM floor where name_en = :name_test",
            Map.of(
                "name_test", "First Floor"
            ),
            FloorView.class
        );
        return ResponseEntity.ok(apiResponse.success(result));
    }

    @GetMapping("/disabled")
    public ResponseEntity<Object> disabled(@RequestParam Long id, @RequestParam(required = false) @DefaultValue("false") boolean activate) {
        String result = floorService.updateStatus(id, activate);
        return ResponseEntity.ok(apiResponse.success(result));
    }
}
