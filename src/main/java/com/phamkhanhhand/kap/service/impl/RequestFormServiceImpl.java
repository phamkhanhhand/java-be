package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.dto.SearchAdjustmentParamDTO;
import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.model.AdjustmentDetail;
import com.phamkhanhhand.kap.repository.AdjustmentDetailReponsitory;
import com.phamkhanhhand.kap.repository.AdjustmentHistoryReponsitory;
import com.phamkhanhhand.kap.repository.RequestFormReponsitory;
import com.phamkhanhhand.kap.repository.impl.RequestFormRepositoryImpl;
import com.phamkhanhhand.kap.service.AdjustmentHistoryService;
import com.phamkhanhhand.kap.service.RequestFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestFormServiceImpl implements RequestFormService {

    private final RequestFormReponsitory requestFormReponsitory;
    private final RequestFormRepositoryImpl requestFormRepositoryImpl;
    private final AdjustmentDetailReponsitory adjustmentDetailReponsitory;
    private final AdjustmentHistoryReponsitory adjustmentHistoryReponsitory;
    private final AdjustmentHistoryService adjustmentHistoryService;


    /*
    * search request form adjustment, data permision, param
    * phamha Oct 18, 2025
     */
    @Override
    public Page<AdjustmentDTO> search(SearchAdjustmentParamDTO request)
    {
        Page<AdjustmentDTO> rs = requestFormRepositoryImpl.getPaging(request.getSearchValue(), request.getPageIndex(), request.getPageSize());

        return rs;
    }

    @Override
    public Optional<Adjustment> getByID(Long id)
    {

//
//        Optional<Adjustment> rs;
//
//        var cache = redisService.getData("RequestForm_" +id, Adjustment.class);
//
//        if(Objects.isNull(cache)){
//            rs = adjustmentReponsitory.findByBudgetAdjustmentID(id);
//            redisService.saveData("RequestForm_" +id, rs.get());
//        }
//        else
//        {
//            rs=Optional.ofNullable(cache);
//        }
//
//
//
//
        Long idx = 1L;
        return  requestFormRepositoryImpl.findById(idx);

    }


    // <editor-fold desc="RequestForm">

    private  boolean validateSubmit(Adjustment adjustment, List<AdjustmentDetail> details){
        var rs = false;

        //todo
        //author
        //status
        this.validateDetails(details);
        rs = true;

        return rs;

    }

    private  boolean validateDetails(List<AdjustmentDetail> details) {

        //duplicate details
        //amount>=0
        return  true;
    }



    private  boolean validateDetailsCoordition(List<AdjustmentDetail> details) {

        //duplicate details
        //amount>=0
        return  true;
    }


//
//    /*
//    * Submit to Leader A01-A02
//    * phamkhanhhand Oct 11, 2025
//     */
//    @Transactional
//    @Override
//    public CommonApprovalResponseDTO submit(CommonRequestDTO requestDTO)
//    {
//        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
//
//        var requestForm = adjustmentReponsitory
//                .findByBudgetAdjustmentID(requestDTO.getId())
//                .orElseThrow(() -> new RuntimeException ("Không tìm thấy adjustment"));
//
//
//        var requestFormDetails= adjustmentDetailReponsitory.findByBudgetAdjustmentID(requestDTO.getId());
//
//        var isValid = validateSubmit(requestForm, requestFormDetails);
//
//
//        if(isValid){
//            requestForm.setStatus(Enumeration.RequestFormStatus.SUBMIT);
//            for (var item : requestFormDetails) {
//
//                item.setApprovedAmount(item.getAmount());
//                if(StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.ADDITION))
//                {
//                    var changeType = (item.getAmount().compareTo(BigDecimal.ZERO) >=0) ? Enumeration.ChangeType.UP :Enumeration.ChangeType.DOWN;
//                    item.setChangeType(changeType);
//                }
//                if(StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.TRANSFER))
//                {
//                    var changeType = StringUtils.equalsIgnoreCase(item.getParentFlag(), Enumeration.Flag.Y)? Enumeration.ChangeType.DOWN: Enumeration.ChangeType.UP;
//                    item.setChangeType(changeType);
//                }
//            }
//
//            adjustmentReponsitory.save(requestForm);
//            adjustmentDetailReponsitory.saveAll(requestFormDetails);
//
//            //history
//            adjustmentHistoryService.makeHistory(requestDTO.getId(),
//                    currentUserContext.getUsername(),
//                    Enumeration.RequestFormStatus.CREATE,
//                    Enumeration.RequestFormStatus.SUBMIT,
//                    requestDTO.getComment(),
//                    requestForm.getBudgetAdjustmentNo()
//            );
//
//        }
//
//        return CommonApprovalResponseDTO.builder()
//                .id(requestForm.getBudgetAdjustmentID())
//                .status("SUCCESS")
//                .build();
//    }
//
//    /*
//     * Leader reject A02-A03
//     * phamkhanhhand Oct 11, 2025
//     */
//    public CommonApprovalResponseDTO reject(CommonRequestDTO requestDTO)
//    {
//        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
//        var currentUsername = currentUserContext.getUsername();
//
//        String resourceCode = RequestHeaderUtil.getHeader("ResourceCode");
//
//        var requestForm = adjustmentReponsitory
//                .findByBudgetAdjustmentID(requestDTO.getId())
//                .orElseThrow(() -> new RuntimeException ("RequestForm not found"));
//
//        if(!StringUtils.equalsIgnoreCase(requestForm.getStatus(), Enumeration.RequestFormStatus.SUBMIT)){
//            throw new RuntimeException ("Status is not valid");
//        }
//
//        //validate permission
//
//        //todo get scope from admin
//        List<Scope> scopes = adminFeign.getListScopeByPermision(currentUsername,resourceCode);
//
//        //if have permission. TODO more: have data scope permision
//        var hasPermission =scopes.stream().anyMatch(x-> StringUtils.equalsIgnoreCase(Enumeration.Scopes.APPROVE, x.getScopeCode()));
//
//        if(hasPermission)
//        {
//            requestForm.setStatus(Enumeration.RequestFormStatus.REJECT);
//
//            adjustmentReponsitory.save(requestForm);
//            adjustmentHistoryService.makeHistory(requestDTO.getId(),
//                    currentUsername,
//                    Enumeration.RequestFormStatus.SUBMIT,
//                    requestForm.getStatus(),
//                    requestDTO.getComment(),
//                    requestForm.getBudgetAdjustmentNo()
//                    );
//        }
//        else{
//                throw new RuntimeException("no permission");
//        }
//
//        return CommonApprovalResponseDTO.builder()
//                .id(requestForm.getBudgetAdjustmentID())
//                .status("SUCCESS")
//                .build();
//    }
//
//
//
//    /*
//     * Leader reject A02-A04
//     * phamkhanhhand Oct 11, 2025
//     */
//    @Transactional
//    @Override
//    public CommonApprovalResponseDTO approve(CommonRequestDTO requestDTO)
//    {
//        if(Objects.equals(requestDTO.getAction(), Enumeration.ApproveAction.REJECT.getValue()))
//        {
//            return reject(requestDTO);
//        }
//
//        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
//        var currentUsername = currentUserContext.getUsername();
//
//        String resourceCode = RequestHeaderUtil.getHeader("ResourceCode");
//
//        var requestForm = adjustmentReponsitory
//                .findByBudgetAdjustmentID(requestDTO.getId())
//                .orElseThrow(() -> new RuntimeException ("RequestForm not found"));
//
//
//        var requestFormDetails= adjustmentDetailReponsitory.findByBudgetAdjustmentID(requestDTO.getId());
//
//
//        if(!StringUtils.equalsIgnoreCase(requestForm.getStatus(), Enumeration.RequestFormStatus.SUBMIT)){
//            throw new RuntimeException ("Status is not valid");
//        }
//
//        //validate permission
//
//        //todo get scope from admin
//        List<Scope> scopes = adminFeign.getListScopeByPermision(currentUsername,resourceCode);
//
//        //if have permission. TODO more: have data scope permision
//        var hasPermission =scopes.stream().anyMatch(x-> StringUtils.equalsIgnoreCase(Enumeration.Scopes.APPROVE, x.getScopeCode()));
//
//        if(hasPermission)
//        {
//            requestForm.setStatus(Enumeration.RequestFormStatus.APPROVE);
//
//            adjustmentReponsitory.save(requestForm);
//            adjustmentHistoryService.makeHistory(requestDTO.getId(),
//                    currentUsername,
//                    Enumeration.RequestFormStatus.SUBMIT,
//                    requestForm.getStatus(),
//                    requestDTO.getComment(),
//                    requestForm.getBudgetAdjustmentNo()
//            );
//        }
//        else{
//            throw new RuntimeException("no permission");
//        }
//
//        return CommonApprovalResponseDTO.builder()
//                .id(requestForm.getBudgetAdjustmentID())
//                .status("SUCCESS")
//                .build();
//        }
//
//
//
//    /*
//     * Leader reject A04-A06
//     * phamkhanhhand Oct 11, 2025
//     */
//    @Transactional
//    @Override
//    public CommonApprovalResponseDTO complete(CommonRequestDTO requestDTO)
//    {
//        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
//        var currentUsername = currentUserContext.getUsername();
//
//        String resourceCode = RequestHeaderUtil.getHeader("ResourceCode");
//
//        var requestForm = adjustmentReponsitory
//                .findByBudgetAdjustmentID(requestDTO.getId())
//                .orElseThrow(() -> new RuntimeException ("RequestForm not found"));
//
//        var requestFormDetails= adjustmentDetailReponsitory.findByBudgetAdjustmentID(requestDTO.getId());
//
//        validateDetailsCoordition(requestFormDetails);
//
//        if(!StringUtils.equalsIgnoreCase(requestForm.getStatus(), Enumeration.RequestFormStatus.APPROVE)){
//            throw new RuntimeException ("Status is not valid");
//        }
//
//        //validate permission
//
//        //todo get scope from admin
//        List<Scope> scopes = adminFeign.getListScopeByPermision(currentUsername,resourceCode);
//
//        //if have permission. TODO more: have data scope permision
//        var hasPermission =scopes.stream().anyMatch(x-> StringUtils.equalsIgnoreCase(Enumeration.Scopes.COMPLETE, x.getScopeCode()));
//
//        if(hasPermission)
//        {
//            requestForm.setStatus(Enumeration.RequestFormStatus.COMPLETE);
//
//            for (var item : requestFormDetails) {
//
//                if(StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.ADDITION))
//                {
//                    var changeType = (item.getApprovedAmount().compareTo(BigDecimal.ZERO) >=0) ? Enumeration.ChangeType.UP :Enumeration.ChangeType.DOWN;
//                    item.setApprovedChangeType(changeType);
//                }
//                if(StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.TRANSFER))
//                {
//                    var changeType = StringUtils.equalsIgnoreCase(item.getParentFlag(), Enumeration.Flag.Y)? Enumeration.ChangeType.DOWN: Enumeration.ChangeType.UP;
//                    item.setApprovedChangeType(changeType);
//                }
//            }
//
//            adjustmentReponsitory.save(requestForm);
//            adjustmentHistoryService.makeHistory(requestDTO.getId(),
//                    currentUsername,
//                    Enumeration.RequestFormStatus.APPROVE,
//                    requestForm.getStatus(),
//                    requestDTO.getComment(),
//                    requestForm.getBudgetAdjustmentNo()
//            );
//        }
//        else{
//            throw new RuntimeException("no permission");
//        }
//
//        return CommonApprovalResponseDTO.builder()
//                .id(requestForm.getBudgetAdjustmentID())
//                .status("SUCCESS")
//                .build();
//    }

    // </editor-fold>

}
