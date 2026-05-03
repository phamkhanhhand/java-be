package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.AuthAccounts;
import com.phamkhanhhand.kap.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResourceReponsitory extends JpaRepository<Resource, Long> {


}