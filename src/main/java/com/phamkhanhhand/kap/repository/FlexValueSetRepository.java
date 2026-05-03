package com.phamkhanhhand.kap.repository;

import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.model.FlexValueSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlexValueSetRepository extends JpaRepository<FlexValueSet, Long> {




}