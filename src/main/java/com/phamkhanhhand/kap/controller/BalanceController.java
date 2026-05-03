package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.BalanceDTO;
import com.phamkhanhhand.kap.dto.BalanceResultDto;
import com.phamkhanhhand.kap.dto.BudgetRequestDTO;
import com.phamkhanhhand.kap.security.DataUserContext;
import com.phamkhanhhand.kap.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/budgets")
public class BalanceController {

    private final String headMapping = "/api/budgets";
    private final BalanceService balanceService;


    /*
    * hold budget from balances
    * phamkhanhhand Oct 04, 2025
     */
    @PostMapping("hold")
//    @CheckPermission(uri = headMapping +"/hold", scopes = {Enumeration.Scopes.EDIT})
    public BalanceResultDto hold(@RequestBody BudgetRequestDTO budgetRequestDTO) {
        return balanceService.hold(budgetRequestDTO);
    }

    /*
     * hold budget from balances
     * phamkhanhhand Oct 04, 2025
     */
    @PostMapping("revert")
    public boolean revert(@RequestBody List<Integer> listBudgetRequestID) {
        return balanceService.revert(listBudgetRequestID);
    }

    @GetMapping
    public List<BalanceDTO> getBudgets() {

        DataUserContext ctx = UserContextUtil.getCurrentUserContext();
        if (ctx == null) {
            throw new RuntimeException("User not authenticated");
        }


        var x = balanceService.getAll();

        return x;
    }

}
