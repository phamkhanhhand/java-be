package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceReponsitory extends JpaRepository<Balance, Long> {
    Optional<Balance> findByBalanceID(int balanceID);
}