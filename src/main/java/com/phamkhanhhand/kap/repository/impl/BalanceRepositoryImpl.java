package com.phamkhanhhand.kap.repository.impl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.*;
import com.phamkhanhhand.kap.dto.BudgetRequestDTO;
import com.phamkhanhhand.kap.dto.BudgetRequestDetailDTO;
import com.phamkhanhhand.kap.security.DataUserContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;


import java.sql.CallableStatement;
import java.util.UUID;


@RequiredArgsConstructor
@Repository
public class BalanceRepositoryImpl extends BaseRepositoryImpl {

    private final JdbcTemplate jdbcTemplate;


    public BalanceResultDto hold(BudgetRequestDTO budgetRequestDTO) {

        BalanceResultDto resultObj = new BalanceResultDto();

        jdbcTemplate.execute((Connection con) -> {

            SQLServerConnection sqlCon = con.unwrap(SQLServerConnection.class);

            // ===== TVP =====
            SQLServerDataTable tvp = new SQLServerDataTable();

            tvp.addColumnMetadata("source_header_id", Types.BIGINT);
            tvp.addColumnMetadata("source_detail_id", Types.BIGINT);
            tvp.addColumnMetadata("balance_id", Types.BIGINT);
            tvp.addColumnMetadata("amount", Types.DECIMAL);

            tvp.addColumnMetadata("segment1", Types.NVARCHAR);
            tvp.addColumnMetadata("segment2", Types.NVARCHAR);
            tvp.addColumnMetadata("segment3", Types.NVARCHAR);
            tvp.addColumnMetadata("segment4", Types.NVARCHAR);
            tvp.addColumnMetadata("segment5", Types.NVARCHAR);
            tvp.addColumnMetadata("segment6", Types.NVARCHAR);
            tvp.addColumnMetadata("segment7", Types.NVARCHAR);
            tvp.addColumnMetadata("segment8", Types.NVARCHAR);
            tvp.addColumnMetadata("segment9", Types.NVARCHAR);
            tvp.addColumnMetadata("segment10", Types.NVARCHAR);
            tvp.addColumnMetadata("sort_order", Types.BIGINT);
            tvp.addColumnMetadata("sort_order_show", Types.NVARCHAR);
            tvp.addColumnMetadata("description", Types.NVARCHAR);


            for (BudgetRequestDetailDTO dto : budgetRequestDTO.getDetails()) {
                tvp.addRow(
                        dto.getHeaderID(),
                        dto.getDetailID(),
                        dto.getBalanceID(),
                        dto.getAmount(),
                        dto.getSegment1(),
                        dto.getSegment2(),
                        dto.getSegment3(),
                        dto.getSegment4(),
                        dto.getSegment5(),
                        dto.getSegment6(),
                        dto.getSegment7(),
                        dto.getSegment8(),
                        dto.getSegment9(),
                        dto.getSegment10()
                );
            }

            String username = UserContextUtil.getCurrentUsername();
            UUID tempID = UUID.randomUUID();
            int year = Year.now().getValue();

            try (CallableStatement cs = sqlCon.prepareCall(
                    "{call [bud].[bud_proc_hold_balance](?,?,?,?,?)}")) {

                cs.setObject(1, tempID);
                cs.setString(2, username);
                cs.setInt(3, year);

                ((SQLServerCallableStatement) cs)
                        .setStructured(4, "bud.bud_type_check_balance_detail", tvp);

                cs.registerOutParameter(5, Types.INTEGER);

                boolean hasResults = cs.execute();

                List<ErrorBalanceDto> errors = new ArrayList<>();

                while (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        if (rs != null) {
                            errors.addAll(mapResultSet(rs, ErrorBalanceDto.class));
                        }
                    }
                    hasResults = cs.getMoreResults();
                }

                int status = cs.getInt(5);

                resultObj.setStatus(status);
                resultObj.setErrors(errors);

            } catch (Exception e) {
                resultObj.setStatus(2);
                throw new RuntimeException(e);
            }

            return null;
        });

        return resultObj;
    }


    /*
     * revert budget
     * (hoàn trả sotien ngân sách (cộng lại tiền ngân sách)). Bước ngược lại của hold
     * phamha Oct 05, 2025
     */

    @Transactional
    public boolean revert(List<Integer> listBudgetRequestID){
        boolean hasResults =false;

        jdbcTemplate.execute((Connection con) -> {
            SQLServerConnection sqlCon = con.unwrap(SQLServerConnection.class);

            // Tạo TVP
            SQLServerDataTable tvp = new SQLServerDataTable();

            tvp.addColumnMetadata("value", Types.BIGINT);

            for (Integer val : listBudgetRequestID) {
                tvp.addRow( val );
            }

            int yearNow = Year.now().getValue();
//            con.setAutoCommit(false);
            try (CallableStatement cs = sqlCon.prepareCall("{call [bud].[proc_revert_balance](?,?,?)}")) {
                cs.setObject(1, "phamha");
                cs.setObject(2, yearNow);
                ((SQLServerCallableStatement) cs).setStructured(3, "dbo.type_list_bigint", tvp);
//
                cs.setQueryTimeout(120); // 30 seconds

                var re =  cs.execute();

                try (ResultSet rs = cs.getResultSet()) {
//                    while (rs.next()) {
//                        double amount = rs.getDouble("amount");
//                    }
                }

//                con.commit();

            } catch (Exception e) {
//                con.rollback();
                throw e;
            }

            return hasResults;
        });

        return hasResults;
    }


}
