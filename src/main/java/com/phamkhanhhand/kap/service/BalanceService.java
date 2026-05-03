package com.phamkhanhhand.kap.service;

import com.phamkhanhhand.kap.dto.BalanceDTO;
import com.phamkhanhhand.kap.dto.BalanceResultDto;
import com.phamkhanhhand.kap.dto.BudgetRequestDTO;

import java.util.List;


public interface BalanceService {

    List<BalanceDTO> getAll();

    BalanceResultDto hold(BudgetRequestDTO budgetRequestDTO);



    boolean revert(List<Integer> listBudgetRequestID);


}
