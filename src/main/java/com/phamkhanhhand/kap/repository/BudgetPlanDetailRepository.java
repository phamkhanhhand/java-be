package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.BudgetPlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetPlanDetailRepository extends JpaRepository<BudgetPlanDetail, Long>{

}