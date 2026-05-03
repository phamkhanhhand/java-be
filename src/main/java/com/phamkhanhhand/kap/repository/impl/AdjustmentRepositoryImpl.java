package com.phamkhanhhand.kap.repository.impl;

import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.model.Adjustment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Repository
public class AdjustmentRepositoryImpl extends BaseRepositoryImpl {

    public List<Adjustment> findAll() {
        String sql = "SELECT * FROM bud_budget_adjustments";
        return query(sql, Adjustment.class);
    }

    public Page<Adjustment> findByNameContaining(String keyword, Pageable pageable) {
        String sql = "SELECT * FROM bud_budget_adjustments WHERE description LIKE :description";
        String countSql = "SELECT COUNT(*) FROM bud_budget_adjustments WHERE description LIKE :description";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("description", "%" + keyword + "%");


        return null;// queryPage(sql, countSql, Adjustment.class, pageable, params);
    }


    public Optional<Adjustment> findById(Long id) {
        String sql = "SELECT * FROM bud_budget_adjustments WHERE budget_adjustment_id = ?";
        return queryOne(sql, Adjustment.class, id);
    }
//
//    public void insert(Adjustment user) {
//        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
//        execute(sql, user.getName(), user.getEmail());
//    }
//
//    public void update(Adjustment user) {
//        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
//        execute(sql, user.getName(), user.getEmail(), user.getId());
//    }
//
//    public void deleteById(Long id) {
//        String sql = "DELETE FROM users WHERE id = ?";
//        execute(sql, id);
//    }



    public boolean holdByAdjustmentId(Long id, String username) {

        var res= false;

        String sql = "{call [bud].[proc_hold_balance_adjustment](?, ?)}";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, username, id);

        if (!result.isEmpty()) {
            var row = result.get(0); // lấy dòng đầu tiên

            String rsCheck = (String) row.get("result");
            Integer numberAffect = (Integer) row.get("numberAffect");

            if(StringUtils.equalsIgnoreCase(rsCheck, Enumeration.Flag.PASS)){
                res = true;
            }

        }
        return res;
    }


    public boolean holdGAPByAdjustmentId(Long id, String username) {

        var res= false;

        String sql = "{call [bud].[proc_hold_gap_balance_adjustment](?, ?)}";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, username, id);

        if (!result.isEmpty()) {
            var row = result.get(0); // lấy dòng đầu tiên

            String rsCheck = (String) row.get("result");
            Integer numberAffect = (Integer) row.get("numberAffect");

            if(StringUtils.equalsIgnoreCase(rsCheck, Enumeration.Flag.PASS)){
                res = true;
            }

        }
        return res;
    }


    public boolean revertByAdjustmentId(Long id, String username, String revertType) {

        var res= false;

        String sql = "{call [bud].[proc_revert_balance_adjustment](?, ?,?)}";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, username, id, revertType);

        if (!result.isEmpty()) {
            var row = result.get(0); // lấy dòng đầu tiên

            String rsCheck = (String) row.get("result");
            Integer numberAffect = (Integer) row.get("numberAffect");

            if(StringUtils.equalsIgnoreCase(rsCheck, Enumeration.Flag.PASS)){
                res = true;
            }

        }
        return res;
    }

}
