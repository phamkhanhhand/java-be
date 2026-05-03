package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.aspect.CheckPermission;
import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.dto.SearchAdjustmentParamDTO;
import com.phamkhanhhand.kap.feign.UserClient;
import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.service.RequestFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/request-form")
public class RequestFormController {
    private final String headMapping = "/api/request-form";

    private final RequestFormService requestFormService;
//    private final KafkaProducer kafkaProducer;
    private final UserClient userClient;



    @GetMapping("test")
    public Object test() {

        Map<String, Object> param = new HashMap<>();
        param.put("username", "admin");
        param.put("password", "123456");


        var rs = userClient.getCurrentUser(param);

        return rs;
    }



    /*
     * Get by id
     * phamkhanhhand Oct 18, 2025
     */

    @CheckPermission(uri = headMapping +"/search", scopes = {Enumeration.Scopes.VIEW})
    @GetMapping("search")
    public Page<AdjustmentDTO> search(@RequestBody(required = false) SearchAdjustmentParamDTO request) {
        return requestFormService.search(request);
    }

    /*
    * Get by id
    * phamkhanhhand Oct 04, 2025
     */
    @GetMapping("get-by-id/{id}")
    public Optional<Adjustment> getByID(@PathVariable Long id) {

//        kafkaProducer.sendEntity("my-topic","Helo ae");

        return requestFormService.getByID(id);

    }

//
//
//    /*
//     * Send to the leader of department
//     * A01-A02
//     * phamkhanhhand Oct 11, 2025
//     */
//    @PostMapping("submit")
//    @CheckPermission(uri = headMapping +"/submit", scopes = {Enumeration.Scopes.ADD, Enumeration.Scopes.EDIT})
//    public CommonApprovalResponseDTO submit(@RequestBody CommonRequestDTO requestDTO)
//    {
//        return requestFormService.submit(requestDTO);
//    }
//
//
//    /*
//     * Approve A02-> A04
//     * Reject A02-> A03
//     * phamkhanhhand Oct 11, 2025
//     */
//    @PostMapping("approve")
//    @CheckPermission(uri = headMapping +"/approve", scopes = {Enumeration.Scopes.APPROVE})
//    public CommonApprovalResponseDTO approve(@RequestBody CommonRequestDTO requestDTO)
//    {
//        return requestFormService.approve(requestDTO);
//    }
//
//
//    /*
//     * complete A04-A06
//     * phamkhanhhand Oct 11, 2025
//     */
//    @PostMapping("complete")
//    @CheckPermission(uri = headMapping +"/complete", scopes = {Enumeration.Scopes.COMPLETE})
//    public CommonApprovalResponseDTO complete(@RequestBody CommonRequestDTO requestDTO)
//    {
//        return requestFormService.complete(requestDTO);
//    }
//



}
