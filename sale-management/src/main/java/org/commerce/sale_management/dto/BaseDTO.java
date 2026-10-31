package org.commerce.sale_management.dto;

import org.springframework.beans.BeanUtils;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 10/30/2026 6:33 AM
 */
public abstract class BaseDTO<S, T> {
    private S source;
    private T target;

    public BaseDTO(S source, T target) {
        this.source = source;
        this.target = target;
    }

    public void cloneProperties() {
        BeanUtils.copyProperties(this.source, this.target);
    }
}
