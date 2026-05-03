package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.dto.BalanceDTO;
import com.phamkhanhhand.kap.dto.BalanceResultDto;
import com.phamkhanhhand.kap.dto.BudgetRequestDTO;
import com.phamkhanhhand.kap.dto.mapper.BalanceMapper;
import com.phamkhanhhand.kap.intercepter.InvalidException;
import com.phamkhanhhand.kap.repository.BalanceReponsitory;
import com.phamkhanhhand.kap.repository.impl.BalanceRepositoryImpl;
import com.phamkhanhhand.kap.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
//@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private   BalanceMapper balanceMapper;
    @Autowired
    private   BalanceReponsitory balanceReponsitory;

    @Autowired
    private BalanceRepositoryImpl balanceReponsitoryImpl;

    @Override
    public List<BalanceDTO> getAll() {
        var entities = balanceReponsitory.findAll();
        return entities.stream()
                .map(balanceMapper::toDTO)
                .toList();
    }


    @Override
    public BalanceResultDto hold(BudgetRequestDTO budgetRequestDTO)
    {
        var rs = balanceReponsitoryImpl.hold(budgetRequestDTO);


        if(Objects.equals(rs.getStatus(), 0))
        {

            throw new InvalidException("Không thành công", rs);
        }


        return  rs;
    }


    @Override
    public boolean revert(List<Integer> listBudgetRequestID)
    {

        boolean hasResults = false;
        int maxRetries = 3;
        int retryDelayMillis = 3000;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {

                hasResults = balanceReponsitoryImpl.revert(listBudgetRequestID);
                break;
            }


            catch (Exception ex) {
                // Check Is there lock/deadlock, time out
                if ((ex instanceof UncategorizedSQLException  && isLockException(  ((UncategorizedSQLException) ex).getSQLException()))
                       || (ex instanceof SQLException && isLockException((SQLException) ex))
                              || ex instanceof QueryTimeoutException)
                {
                    if (attempt == maxRetries) throw ex; // Over time retry
                    try {
                        Thread.sleep(retryDelayMillis); // wait 3 seconds
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException("Retry interrupted", ie);
                    }
                } else {
                    throw ex; // not lock
                }
            }

        }

        return hasResults;

    }

    private boolean isLockException(SQLException ex) {
        int errorCode = ex.getErrorCode();

        return errorCode == 1205 ||  // Deadlock
                errorCode == 1222 ||  // Lock timeout
                errorCode == -2;      // JDBC Command timeout
    }

    public List<BalanceDTO> confirm(BudgetRequestDTO budgetRequestDTO)
    {
        return null;
    }

    public List<BalanceDTO> revert(BudgetRequestDTO budgetRequestDTO)
    {
        return null;
    }

    public List<BalanceDTO> check(BudgetRequestDTO budgetRequestDTO)
    {
        return null;
    }
}
