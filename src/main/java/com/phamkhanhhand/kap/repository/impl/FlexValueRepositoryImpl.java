package com.phamkhanhhand.kap.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class FlexValueRepositoryImpl extends BaseRepositoryImpl {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public List<String> selectFlexValue(String flexValueSetName){
        String sql = """
            SELECT trim(flex_value)
            FROM adm_flex_values a
            join adm_flex_value_sets b on a.flex_value_set_id=b.flex_value_set_id
            WHERE b.flex_value_set_name = ?
        """;
        return jdbcTemplate.queryForList(sql, String.class,flexValueSetName);
    }
}
