package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.Adjustment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdjustmentReponsitory extends JpaRepository<Adjustment, Long> {
    Optional<Adjustment> findByBudgetAdjustmentID(Long budgetAdjustmentID);
}