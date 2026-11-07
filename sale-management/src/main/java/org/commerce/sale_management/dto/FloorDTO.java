package org.commerce.sale_management.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.commerce.sale_management.model.Floor;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 10:47 PM
 */
@Setter
@NoArgsConstructor
public class FloorDTO extends Floor implements BaseView {
    public FloorDTO(Floor org) {
        this.copyAllIncludeProperties(org);
    }
}
