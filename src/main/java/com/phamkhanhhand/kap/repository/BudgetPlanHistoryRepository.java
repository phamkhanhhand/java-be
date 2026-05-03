package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.BudgetPlanHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanHistoryRepository extends JpaRepository<BudgetPlanHistory, Long>{


}