package com.phamkhanhhand.kap.repository.impl;

import com.phamkhanhhand.kap.dto.FlexValueSetDto;
import com.phamkhanhhand.kap.model.Adjustment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class FlexValueSetRepositoryImpl extends BaseRepositoryImpl {
    @Autowired
    protected JdbcTemplate jdbcTemplate;

    public Page<FlexValueSetDto> getPagingFlexValueSet(

    ) {
        String sql = "SELECT * FROM bud.adm_flex_value_sets";
        String sqlCount = "SELECT count(*) FROM bud.adm_flex_value_sets";
        Map<String, Object> params = new HashMap<>();

        Pageable pageable = PageRequest.of(1, 10);

        return queryPage(sql, sqlCount,FlexValueSetDto.class, pageable,params );
    }
}
