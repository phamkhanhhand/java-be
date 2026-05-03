package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.BudgetPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanRepository extends JpaRepository<BudgetPlan, Long>{


}