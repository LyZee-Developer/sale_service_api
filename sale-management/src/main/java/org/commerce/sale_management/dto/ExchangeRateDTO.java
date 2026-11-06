package org.commerce.sale_management.dto;

import org.commerce.sale_management.model.ExchangeRate;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 9:32 PM
 */
public class ExchangeRateDTO extends ExchangeRate implements BaseView {
    public ExchangeRateDTO(ExchangeRate org) {
        this.copyAllIncludeProperties(org);
    }
}
