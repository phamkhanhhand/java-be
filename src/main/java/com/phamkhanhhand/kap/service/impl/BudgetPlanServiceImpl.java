package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.common.Constant;
import com.phamkhanhhand.kap.common.Enumeration;

import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.BudgetPlanDTO;
import com.phamkhanhhand.kap.dto.BudgetPlanDetailDTO;
import com.phamkhanhhand.kap.dto.CommonApprovalResponseDTO;
import com.phamkhanhhand.kap.dto.CommonRequestDTO;
import com.phamkhanhhand.kap.model.BudgetPlan;
import com.phamkhanhhand.kap.model.BudgetPlanDetail;
import com.phamkhanhhand.kap.model.BudgetPlanHistory;
import com.phamkhanhhand.kap.repository.BudgetPlanDetailRepository;
import com.phamkhanhhand.kap.repository.BudgetPlanHistoryRepository;
import com.phamkhanhhand.kap.repository.BudgetPlanRepository;
import com.phamkhanhhand.kap.repository.impl.FlexValueRepositoryImpl;
import com.phamkhanhhand.kap.service.BudgetPlanService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetPlanServiceImpl implements BudgetPlanService {
    private final BudgetPlanRepository budgetPlanRepository;
    private final BudgetPlanDetailRepository budgetPlanDetailRepository;
    private final BudgetPlanHistoryRepository budgetPlanHistoryRepository;
    private final FlexValueRepositoryImpl flexValueRepositoryImpl;


    boolean validatePlan(BudgetPlanDTO dtos){

        boolean rs = true;
        List<String> listErr = new ArrayList<>();

        var currentYear = Year.now().getValue() +"";
        if(!StringUtils.equalsIgnoreCase(currentYear,dtos.getPeriod()))
        {
//            throw new RuntimeException("Kỳ không hợp lệ");
            listErr.add("Kỳ ngân sách không hợp lệ (phải là năm hiện tại)");
        }

        //validate detail
        var details = dtos.getListBudgetPlanDetail();
        if (details==null||details.isEmpty()){
            listErr.add("Danh sách chi tiết ngân sách không được trống");
        }
        //lấy danh sách các segment
        //segment : branch, division, category
        List<String> listBranch = flexValueRepositoryImpl.selectFlexValue(Constant.FlexValue.BRANCH_LIST);
        List<String> listDivision=flexValueRepositoryImpl.selectFlexValue(Constant.FlexValue.DIVISION_LIST);
        List<String> listCategory=flexValueRepositoryImpl.selectFlexValue(Constant.FlexValue.CATEGORY_LIST);
        var i = 1;
        for (var detail :details) {
            if (!listBranch.contains(detail.getSegment1().trim())) {
                listErr.add("Dòng " + i + " chi nhánh " + detail.getSegment1().trim()+ " không có trong danh mục");
            }
            if (!listDivision.contains(detail.getSegment2().trim())) {
                listErr.add("Dòng " + i + " khối " + detail.getSegment2().trim() + " không có trong danh mục");

            }
            if (!listCategory.contains(detail.getSegment3().trim())) {
                listErr.add("Dòng " + i + " Hạng mục " + detail.getSegment3().trim() + " không có trong danh mục");
            }
            i++;
        }

        if(listErr.size()>0){
            rs=false;
            throw new RuntimeException(String.join("; ", listErr));
        }

        return rs;
    }

    private List<BudgetPlanDetail> saveDetails(Long budgetPlanId, List<BudgetPlanDetailDTO> PlanDetaildto) {
        List<BudgetPlanDetail> listUpdate = PlanDetaildto.stream()
                .filter(x -> !x.isDeleted())
                .map(dto -> {
                    BudgetPlanDetail entity = new BudgetPlanDetail();
                    BeanUtils.copyProperties(dto, entity);
                    entity.setBudgetPlanId(budgetPlanId);
                    return entity;
                })
                .collect(Collectors.toList());

        return budgetPlanDetailRepository.saveAll(listUpdate);
    }

    private void createHistory(Long id, String username, String fromStatus, String toStatus, String comment, String budgetLine) {
        Date now = new Date();
        BudgetPlanHistory history = BudgetPlanHistory.builder()
                .budgetPlanId(id)
                .budgetLine(budgetLine)
                .fromStatus(fromStatus)
                .toStatus(toStatus)
                .comment(comment)
                .createdBy(username)
                .createdDate(now)
                .build();
        budgetPlanHistoryRepository.save(history);
    }

    @Override
    @Transactional
    public BudgetPlanDTO create(BudgetPlanDTO dtos) {

        validatePlan(dtos);

        BudgetPlan plan=BudgetPlan.builder().build();
        BeanUtils.copyProperties(dtos,plan);
        plan.setStatus(Enumeration.RequestFormStatus.CREATE);
        budgetPlanRepository.save(plan);

        // save detail
        var listDetail=dtos.getListBudgetPlanDetail();
        saveDetails(plan.getBudgetPlanId(),listDetail);

        //delete details( isDelete=true)
        var listDeleteID= listDetail.stream()
                .filter(x-> x.isDeleted())
                .map(x->x.getBudgetPlanDetailId())
                .toList();
        budgetPlanDetailRepository.deleteAllByIdInBatch(listDeleteID);
        return dtos;
    }

    public List<BudgetPlan> findAll(){
        return budgetPlanRepository.findAll();
    }

    @Transactional
    @Override
    public CommonApprovalResponseDTO submit(CommonRequestDTO requestDTO) {

        BudgetPlan plan = budgetPlanRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("BudgetPlan not found"));

        if (!StringUtils.equalsIgnoreCase(Enumeration.RequestFormStatus.CREATE,plan.getStatus().trim())) {
            throw new RuntimeException("Chỉ được trình khi trạng thái là tạo mới");
        }


       var currentUsername = UserContextUtil.getCurrentUsername();

        plan.setStatus(Enumeration.RequestFormStatus.SUBMIT);
        budgetPlanRepository.save(plan);
        // lưu history
        createHistory(plan.getBudgetPlanId(), currentUsername,
                Enumeration.RequestFormStatus.CREATE, Enumeration.RequestFormStatus.SUBMIT,
                requestDTO.getComment(), plan.getBudgetLine()
        );
        return CommonApprovalResponseDTO.builder()
                .id(plan.getBudgetPlanId())
                .build();
    }

    @Override
    @Transactional
    public CommonApprovalResponseDTO reject(CommonRequestDTO requestDTO){
        BudgetPlan plan=budgetPlanRepository.findById(requestDTO.getId())
                .orElseThrow(()->new RuntimeException("BudgetPlan not found"));
        if(!StringUtils.equalsIgnoreCase(Enumeration.RequestFormStatus.SUBMIT,plan.getStatus())){
            throw new RuntimeException("Chỉ được duyệt khi đã trình lãnh đạo");
        }
        plan.setStatus(Enumeration.RequestFormStatus.REJECT);
        budgetPlanRepository.save(plan);
        createHistory(requestDTO.getId()," "
                ,Enumeration.RequestFormStatus.SUBMIT, Enumeration.RequestFormStatus.REJECT
                , requestDTO.getComment(), plan.getBudgetLine());
        return CommonApprovalResponseDTO.builder().id(plan.getBudgetPlanId()).build();
    }

    @Override
    @Transactional
    public CommonApprovalResponseDTO approve(CommonRequestDTO requestDTO){

        BudgetPlan plan = budgetPlanRepository.findById(requestDTO.getId())
                .orElseThrow(() -> new RuntimeException("BudgetPlan not found"));

        if (!StringUtils.equalsIgnoreCase(Enumeration.RequestFormStatus.SUBMIT,plan.getStatus())){
            throw new RuntimeException("Chỉ được duyệt khi đã trình lãnh đạo");
        }
        plan.setStatus(Enumeration.RequestFormStatus.APPROVE);
        budgetPlanRepository.save(plan);
        createHistory(plan.getBudgetPlanId(), ""
                , Enumeration.RequestFormStatus.SUBMIT, Enumeration.RequestFormStatus.APPROVE
                , requestDTO.getComment(), plan.getBudgetLine());
        return CommonApprovalResponseDTO.builder().id(plan.getBudgetPlanId()).build();
    }


    @Override
    @Transactional
    public CommonApprovalResponseDTO cancel(CommonRequestDTO requestDTO){
        BudgetPlan plan=budgetPlanRepository.findById(requestDTO.getId())
                .orElseThrow(()->new RuntimeException("BudgetPlan not found"));

        if(StringUtils.equalsIgnoreCase(Enumeration.RequestFormStatus.CANCEL,plan.getStatus())){
            throw new RuntimeException("kế hoạch này đã được hủy rồi");
        }
        plan.setStatus(Enumeration.RequestFormStatus.CANCEL);
        budgetPlanRepository.save(plan);
        createHistory(plan.getBudgetPlanId(), " "
                , plan.getStatus(), Enumeration.RequestFormStatus.CANCEL
                , requestDTO.getComment(), plan.getBudgetLine());
        return CommonApprovalResponseDTO.builder().id(plan.getBudgetPlanId()).build();
    } 
}