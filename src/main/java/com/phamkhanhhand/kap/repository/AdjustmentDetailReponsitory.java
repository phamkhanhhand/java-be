package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.AdjustmentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdjustmentDetailReponsitory extends JpaRepository<AdjustmentDetail, Long> {
    List<AdjustmentDetail> findByBudgetAdjustmentID(Long budgetAdjustmentID);
}