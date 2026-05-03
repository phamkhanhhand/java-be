package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.common.RequestHeaderUtil;
import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.AdjustmentDTO;
import com.phamkhanhhand.kap.dto.CommonApprovalResponseDTO;
import com.phamkhanhhand.kap.dto.CommonRequestDTO;
import com.phamkhanhhand.kap.dto.mapper.AdjustmentMapper;
import com.phamkhanhhand.kap.feign.AdminFeign;
import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.model.AdjustmentDetail;
import com.phamkhanhhand.kap.model.Scope;
import com.phamkhanhhand.kap.repository.AdjustmentDetailReponsitory;
import com.phamkhanhhand.kap.repository.AdjustmentReponsitory;
import com.phamkhanhhand.kap.repository.impl.AdjustmentRepositoryImpl;
import com.phamkhanhhand.kap.security.DataUserContext;
import com.phamkhanhhand.kap.service.AdjustmentHistoryService;
import com.phamkhanhhand.kap.service.AdjustmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AdjustmentServiceImpl implements AdjustmentService {

    private final AdjustmentMapper adjustmentMapper;
    private final AdjustmentReponsitory adjustmentReponsitory;
    private final AdjustmentRepositoryImpl adjustmentRepositoryImpl;
    private final AdjustmentDetailReponsitory adjustmentDetailReponsitory;
    private final AdjustmentHistoryService adjustmentHistoryService;
    private final AdminFeign adminFeign;


    @Override
    public List<AdjustmentDTO> getAll() {
        var entities = adjustmentRepositoryImpl.findAll();
        return entities.stream()
                .map(adjustmentMapper::toDTO)
                .toList();
    }


    @Override
    public AdjustmentDTO getByID(Long id) {
        Optional<Adjustment> rs = adjustmentReponsitory.findByBudgetAdjustmentID(id);

        return adjustmentMapper.toDTO(rs.get());

    }

    private boolean validateSubmit(Adjustment adjustment, List<AdjustmentDetail> details) {
        var rs = false;

        //todo
        //author
        //status
        this.validateDetails(details);
        rs = true;

        return rs;

    }

    private boolean validateDetails(List<AdjustmentDetail> details) {

        //duplicate details
        //amount>=0
        return true;
    }


    private boolean validateDetailsCoordition(List<AdjustmentDetail> details) {

        //duplicate details
        //amount>=0
        return true;
    }


    /*
     * Submit to Leader A01-A02
     * hold down type to balance
     * phamkhanhhand Oct 11, 2025
     */
    @Transactional
    @Override
    public CommonApprovalResponseDTO submit(CommonRequestDTO requestDTO) {
        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
        var rs = CommonApprovalResponseDTO.builder()
                .id(requestDTO.getId())
                .status(Enumeration.Flag.PASS)
                .build();


        var adjustment = adjustmentReponsitory
                .findByBudgetAdjustmentID(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy adjustment"));


        var requestFormDetails = adjustmentDetailReponsitory.findByBudgetAdjustmentID(requestDTO.getId());

        var isValid = validateSubmit(adjustment, requestFormDetails);


        if (isValid) {

            var holdResult = adjustmentRepositoryImpl.holdByAdjustmentId(adjustment.getBudgetAdjustmentID(), currentUserContext.getUsername());

            if (holdResult) {


                adjustment.setStatus(Enumeration.RequestFormStatus.SUBMIT);
                for (var item : requestFormDetails) {

                    item.setApprovedAmount(item.getAmount());
                    if (StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.ADDITION)) {
                        var changeType = (item.getAmount().compareTo(BigDecimal.ZERO) >= 0) ? Enumeration.ChangeType.UP : Enumeration.ChangeType.DOWN;
                        item.setChangeType(changeType);
                    }
                    if (StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.TRANSFER)) {
                        var changeType = StringUtils.equalsIgnoreCase(item.getParentFlag(), Enumeration.Flag.Y) ? Enumeration.ChangeType.DOWN : Enumeration.ChangeType.UP;
                        item.setChangeType(changeType);
                    }
                }

                adjustmentReponsitory.save(adjustment);
                adjustmentDetailReponsitory.saveAll(requestFormDetails);

                //history
                adjustmentHistoryService.makeHistory(requestDTO.getId(),
                        currentUserContext.getUsername(),
                        Enumeration.RequestFormStatus.CREATE,
                        Enumeration.RequestFormStatus.SUBMIT,
                        requestDTO.getComment(),
                        adjustment.getBudgetAdjustmentNo()
                );

            } else {
                rs = CommonApprovalResponseDTO.builder()
                        .id(requestDTO.getId())
                        .status(Enumeration.Flag.FAIL)
                        .errorMessage("Khong cat duoc ngan sach")
                        .build();
            }
        }

        return rs;
    }

    /*
     * Leader reject A02-A03
     * phamkhanhhand Oct 11, 2025
     */
    public CommonApprovalResponseDTO reject(CommonRequestDTO requestDTO) {
        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
        var currentUsername = currentUserContext.getUsername();

        var rs = CommonApprovalResponseDTO.builder()
                .id(requestDTO.getId())
                .status(Enumeration.Flag.PASS)
                .build();

        String resourceCode = RequestHeaderUtil.getHeader("ResourceCode");

        var adjustment = adjustmentReponsitory
                .findByBudgetAdjustmentID(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("adjustment not found"));

        if (!StringUtils.equalsIgnoreCase(adjustment.getStatus(), Enumeration.AdjustmentStatus.SUBMIT)) {
            throw new RuntimeException("Status is not valid");
        }

        //validate permission

        //todo get scope from admin
        List<Scope> scopes = adminFeign.getListScopeByPermision(currentUsername, resourceCode);

        //if have permission. TODO more: have data scope permision
        var hasPermission = scopes.stream().anyMatch(x -> StringUtils.equalsIgnoreCase(Enumeration.Scopes.APPROVE, x.getScopeCode()));

        if (hasPermission) {

            //revert

            var holdResult = adjustmentRepositoryImpl.revertByAdjustmentId(adjustment.getBudgetAdjustmentID(), currentUserContext.getUsername(), Enumeration.RevertType.ADJUSTMENT);

            if (holdResult) {

                adjustment.setStatus(Enumeration.AdjustmentStatus.REJECT);

                adjustmentReponsitory.save(adjustment);
                adjustmentHistoryService.makeHistory(requestDTO.getId(),
                        currentUsername,
                        Enumeration.AdjustmentStatus.SUBMIT,
                        adjustment.getStatus(),
                        requestDTO.getComment(),
                        adjustment.getBudgetAdjustmentNo()
                );
            } else {
                rs = CommonApprovalResponseDTO.builder()
                        .id(requestDTO.getId())
                        .status(Enumeration.Flag.FAIL)
                        .errorMessage("Khong cat duoc ngan sach")
                        .build();
            }


        } else {
            throw new RuntimeException("no permission");
        }

        return rs;
    }


    /*
     * Leader reject A02-A04
     * phamkhanhhand Oct 11, 2025
     */
    @Transactional
    @Override
    public CommonApprovalResponseDTO approve(CommonRequestDTO requestDTO) {
        if (Objects.equals(requestDTO.getAction(), Enumeration.ApproveAction.REJECT.getValue())) {
            return reject(requestDTO);
        }

        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
        var currentUsername = currentUserContext.getUsername();

        String resourceCode = RequestHeaderUtil.getHeader("ResourceCode");

        var requestForm = adjustmentReponsitory
                .findByBudgetAdjustmentID(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("RequestForm not found"));


        if (!StringUtils.equalsIgnoreCase(requestForm.getStatus(), Enumeration.RequestFormStatus.SUBMIT)) {
            throw new RuntimeException("Status is not valid");
        }

        //validate permission

        //todo get scope from admin
        List<Scope> scopes = adminFeign.getListScopeByPermision(currentUsername, resourceCode);

        //if have permission. TODO more: have data scope permision
        var hasPermission = scopes.stream().anyMatch(x -> StringUtils.equalsIgnoreCase(Enumeration.Scopes.APPROVE, x.getScopeCode()));

        if (hasPermission) {
            requestForm.setStatus(Enumeration.RequestFormStatus.APPROVE);

            adjustmentReponsitory.save(requestForm);
            adjustmentHistoryService.makeHistory(requestDTO.getId(),
                    currentUsername,
                    Enumeration.RequestFormStatus.SUBMIT,
                    requestForm.getStatus(),
                    requestDTO.getComment(),
                    requestForm.getBudgetAdjustmentNo()
            );
        } else {
            throw new RuntimeException("no permission");
        }

        return CommonApprovalResponseDTO.builder()
                .id(requestForm.getBudgetAdjustmentID())
                .status("SUCCESS")
                .build();
    }

    /*
     * Leader reject A04-A06
     * phamkhanhhand Oct 11, 2025
     */
    @Transactional
    @Override
    public CommonApprovalResponseDTO complete(CommonRequestDTO requestDTO) {
        var rs = CommonApprovalResponseDTO.builder()
                .id(requestDTO.getId())
                .status(Enumeration.Flag.PASS)
                .build();

        DataUserContext currentUserContext = UserContextUtil.getCurrentUserContext();
        var currentUsername = currentUserContext.getUsername();

        String resourceCode = RequestHeaderUtil.getHeader("ResourceCode");

        var adjustment = adjustmentReponsitory
                .findByBudgetAdjustmentID(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("adjustment not found"));

        var adjustmentDetails = adjustmentDetailReponsitory.findByBudgetAdjustmentID(requestDTO.getId());

        validateDetailsCoordition(adjustmentDetails);

        if (!StringUtils.equalsIgnoreCase(adjustment.getStatus(), Enumeration.AdjustmentStatus.APPROVE)) {
            throw new RuntimeException("Status is not valid");
        }

        //validate permission

        //todo get scope from admin
        List<Scope> scopes = adminFeign.getListScopeByPermision(currentUsername, resourceCode);

        //if have permission. TODO more: have data scope permision
        var hasPermission = scopes.stream().anyMatch(x -> StringUtils.equalsIgnoreCase(Enumeration.Scopes.COMPLETE, x.getScopeCode()));

        if (hasPermission) {

            var holdResult = adjustmentRepositoryImpl.holdGAPByAdjustmentId(adjustment.getBudgetAdjustmentID(), currentUserContext.getUsername());

            if (holdResult) {

                adjustment.setStatus(Enumeration.AdjustmentStatus.COMPLETE);

                for (var item : adjustmentDetails) {

                    if (StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.ADDITION)) {
                        var changeType = (item.getApprovedAmount().compareTo(BigDecimal.ZERO) >= 0) ? Enumeration.ChangeType.UP : Enumeration.ChangeType.DOWN;
                        item.setApprovedChangeType(changeType);
                    }
                    if (StringUtils.equalsIgnoreCase(item.getAdjustmentType(), Enumeration.AdjustmentTabType.TRANSFER)) {
                        var changeType = StringUtils.equalsIgnoreCase(item.getParentFlag(), Enumeration.Flag.Y) ? Enumeration.ChangeType.DOWN : Enumeration.ChangeType.UP;
                        item.setApprovedChangeType(changeType);
                    }
                }

                adjustmentReponsitory.save(adjustment);
                adjustmentHistoryService.makeHistory(requestDTO.getId(),
                        currentUsername,
                        Enumeration.AdjustmentStatus.APPROVE,
                        adjustment.getStatus(),
                        requestDTO.getComment(),
                        adjustment.getBudgetAdjustmentNo()
                );
            } else {
                rs = CommonApprovalResponseDTO.builder()
                        .id(requestDTO.getId())
                        .status(Enumeration.Flag.FAIL)
                        .errorMessage("Khong cat duoc ngan sach")
                        .build();
            }
        } else {
            throw new RuntimeException("no permission");
        }

        return rs;
    }

}
