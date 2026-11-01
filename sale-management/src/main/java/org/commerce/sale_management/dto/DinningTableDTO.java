package org.commerce.sale_management.dto;

import lombok.Getter;
import lombok.Setter;
import org.commerce.sale_management.model.DinningTable;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/1/2026 1:44 PM
 */
@Getter
public class DinningTableDTO extends DinningTable implements BaseView {
    public DinningTableDTO(DinningTable org) {
        this.copyAllIncludeProperties(org);
    }
}
