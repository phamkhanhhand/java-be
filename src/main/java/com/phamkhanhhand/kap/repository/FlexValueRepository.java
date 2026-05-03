package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.FlexValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlexValueRepository extends JpaRepository<FlexValue, Long> {

}