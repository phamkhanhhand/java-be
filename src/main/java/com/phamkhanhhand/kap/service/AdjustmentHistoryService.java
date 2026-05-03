package com.phamkhanhhand.kap.service;


public interface AdjustmentHistoryService {
    public void makeHistory(Long id, String username, String fromStatus, String toStatus, String comment, String budgetAdjustmentNo);

    }
