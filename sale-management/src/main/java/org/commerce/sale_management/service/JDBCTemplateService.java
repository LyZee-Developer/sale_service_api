package org.commerce.sale_management.service;

import org.commerce.sale_management.model.Floor;

import java.util.List;
import java.util.Map;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 11:21 PM
 */
public interface JDBCTemplateService {
    <T> List<T> executeScript(String sql, Class<T> dto);

    <T> List<T> executeScript(String sql,Map<String, Object> param, Class<T> dto);
}
