package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.aspect.CheckPermission;
import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.dto.CommonApprovalResponseDTO;
import com.phamkhanhhand.kap.dto.CommonRequestDTO;
import com.phamkhanhhand.kap.service.AdjustmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/adjustment")
public class AdjustmentController {

    private final String headMapping = "/api/adjustment";
    private final AdjustmentService adjustmentService;



    /*
     * Send to the leader of department
     * A01-A02
     * phamkhanhhand Oct 11, 2025
     */
    @PostMapping("submit")
    @CheckPermission(uri = headMapping +"/submit", scopes = {Enumeration.Scopes.ADD, Enumeration.Scopes.EDIT})
    public CommonApprovalResponseDTO submit(@RequestBody CommonRequestDTO requestDTO)
    {
        return adjustmentService.submit(requestDTO);
    }


    /*
     * Approve A02-> A04
     * Reject A02-> A03
     * phamkhanhhand Oct 11, 2025
     */
    @PostMapping("approve")
    @CheckPermission(uri = headMapping +"/approve", scopes = {Enumeration.Scopes.APPROVE})
    public CommonApprovalResponseDTO approve(@RequestBody CommonRequestDTO requestDTO)
    {
        return adjustmentService.approve(requestDTO);
    }


    /*
     * complete A04-A06
     * phamkhanhhand Oct 11, 2025
     */
    @PostMapping("complete")
    @CheckPermission(uri = headMapping +"/complete", scopes = {Enumeration.Scopes.COMPLETE})
    public CommonApprovalResponseDTO complete(@RequestBody CommonRequestDTO requestDTO)
    {
        return adjustmentService.complete(requestDTO);
    }


}
