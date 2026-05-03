package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.model.AdjustmentHistory;
import com.phamkhanhhand.kap.repository.AdjustmentHistoryReponsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AdjustmentBaseServiceImpl {

    private final AdjustmentHistoryReponsitory adjustmentHistoryReponsitory;


    public void makeHistory(Long id, String username, String fromStatus, String toStatus, String comment, String budgetAdjustmentNo){
        Date now = new Date(); // Lưu cả giờ phút giây


        //history
        AdjustmentHistory his = AdjustmentHistory.builder()
                .fromStatus(fromStatus)
                .toStatus(toStatus)
                .comment(comment)
                .createdBy(username)
                .createdDate(now)
                .budgetAdjustmentNo(budgetAdjustmentNo)
                .budgetAdjustmentID(id)
                .build();

        adjustmentHistoryReponsitory.save(his);
    }
}
