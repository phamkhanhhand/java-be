package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.feign.AdminFeign;
import com.phamkhanhhand.kap.model.AdjustmentHistory;
import com.phamkhanhhand.kap.repository.AdjustmentHistoryReponsitory;
import com.phamkhanhhand.kap.service.AdjustmentHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AdjustmentHistoryServiceImpl implements AdjustmentHistoryService {

    private final AdjustmentHistoryReponsitory adjustmentHistoryReponsitory;
    private final AdminFeign adminFeign;


    // </editor-fold>

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
