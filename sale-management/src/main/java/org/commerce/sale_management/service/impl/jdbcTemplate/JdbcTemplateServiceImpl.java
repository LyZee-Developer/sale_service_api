package org.commerce.sale_management.service.impl.jdbcTemplate;

import org.commerce.sale_management.service.JDBCTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author : Ly LeangSeng
 * @email : lyleangseng712@gmail.com
 * @date : 11/6/2026 11:26 PM
 */
@Service
public class JdbcTemplateServiceImpl implements JDBCTemplateService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public <T> List<T> executeScript(String sql, Class<T> viewDTO) {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(viewDTO));
    }

    @Override
    public <T> List<T> executeScript(String sql, Map<String, Object> mapKey, Class<T> viewDTO) {
        return namedParameterJdbcTemplate.query(sql, mapSqlParameter(mapKey), new BeanPropertyRowMapper<>(viewDTO));
    }

    private MapSqlParameterSource mapSqlParameter(Map<String, Object> mapKey) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        for (Map.Entry<String, Object> value : mapKey.entrySet()) {
            parameters.addValue(value.getKey(), value.getValue());
        }
        return parameters;
    }
}
