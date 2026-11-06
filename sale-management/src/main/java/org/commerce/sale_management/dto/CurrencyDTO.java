package org.commerce.sale_management.dto;

import org.commerce.sale_management.model.Currency;
import org.module.publish_service.view.BaseView;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/5/2026 6:52 PM
 */
public class CurrencyDTO extends Currency implements BaseView {
    public CurrencyDTO(Currency org) {
        this.copyAllIncludeProperties(org);
    }
}
