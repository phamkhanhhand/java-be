package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.model.AuthAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthAccountReponsitory extends JpaRepository<AuthAccounts, Long> {

    Optional<AuthAccounts> findByUsername(String username);

}