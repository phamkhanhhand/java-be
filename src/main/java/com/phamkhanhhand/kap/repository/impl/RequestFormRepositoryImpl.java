package com.phamkhanhhand.kap.repository.impl;

import com.phamkhanhhand.kap.common.Constant;
import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.model.Adjustment;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.*;

@RequiredArgsConstructor
@Repository
public class RequestFormRepositoryImpl extends BaseRepositoryImpl {

    public Page<AdjustmentDTO> getPaging(String keyword, Integer pageIndex, Integer pageSize) {


        // <editor-fold desc="prepare">

        if(Objects.isNull(pageIndex)){
            pageIndex = Constant.PageSerch.DefaultPageIndex;
        }
        if(Objects.isNull(pageSize)){
            pageSize = Constant.PageSerch.DefaultPageSize;
        }

        Sort sort = Sort.by(
                Sort.Order.desc("modified_date"),
                Sort.Order.asc("budget_adjustment_no")
        );

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        // </editor-fold>


        var permissionDataCode = "1";
        var currentUsername =   UserContextUtil.getCurrentUsername();


        String sql = """  
                with a as (
                    select distinct unit_code from bud.func_get_data_scope_permission(:currentUsername,:permissionDataCode,NULL)
                )
                SELECT * FROM bud.bud_budget_adjustments b
                inner join a on a.unit_code= b.created_branch_code
                WHERE 1=1
                """;

        String countSql =
                  """  
                with a as (
                 select distinct unit_code from bud.func_get_data_scope_permission(:currentUsername,:permissionDataCode,NULL)
                )
                SELECT COUNT(*) FROM bud.bud_budget_adjustments b
                inner join a on a.unit_code= b.created_branch_code
                WHERE 1=1
                """;


        var searchClause = " ";
        if(Objects.nonNull(keyword)){
            searchClause += " and budget_adjustment_no LIKE :keyword ";
        }

        sql += searchClause;
        countSql += searchClause;

//        MapSqlParameterSource params = new MapSqlParameterSource();
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "%" + keyword + "%");
        params.put("permissionDataCode", permissionDataCode);
        params.put("currentUsername", currentUsername);

        return queryPage(sql, countSql, AdjustmentDTO.class, pageable, params);

    }


    public Optional<Adjustment> findById(Long id) {
        String sql = "SELECT * FROM bud.bud_budget_adjustments WHERE budget_adjustment_id = ?";
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
