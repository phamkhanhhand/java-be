package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.dto.EditFlexValueDto;
import com.phamkhanhhand.kap.dto.EditFlexValueSetDto;
import com.phamkhanhhand.kap.dto.FlexValueSetDto;
import com.phamkhanhhand.kap.dto.PageParamDTO;
import com.phamkhanhhand.kap.model.FlexValue;
import com.phamkhanhhand.kap.model.FlexValueSet;
import com.phamkhanhhand.kap.repository.FlexValueRepository;
import com.phamkhanhhand.kap.repository.FlexValueSetRepository;
import com.phamkhanhhand.kap.repository.impl.FlexValueSetRepositoryImpl;
import com.phamkhanhhand.kap.service.FlexValueSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FlexValueSetServiceImpl implements FlexValueSetService {

    private final FlexValueSetRepositoryImpl flexValueSetRepositoryImpl;
    private final FlexValueSetRepository flexValueSetRepository;
    private final FlexValueRepository flexValueRepository;

    @Override
    public Page<FlexValueSetDto> getPagingFlexValueSet(PageParamDTO query) {

        if (query.getSearchValue() != null) {
            return flexValueSetRepositoryImpl.getPagingFlexValueSet();
        }

        return flexValueSetRepositoryImpl.getPagingFlexValueSet( );
    }


    public FlexValueSet save(EditFlexValueSetDto dto) {

        FlexValueSet entity;

        // UPDATE
        if (dto.getFlexValueSetId() != null) {
            entity = flexValueSetRepository.findById(dto.getFlexValueSetId())
                    .orElseThrow(() -> new RuntimeException("Not found"));

            //entity.setFlexValueSetCode(dto.getFlexValueSetCode());
            entity.setFlexValueSetName(dto.getFlexValueSetName());
            entity.setEnableFlag(dto.getEnableFlag());
            entity.setPeriod(dto.getPeriod());
            entity.setDescription(dto.getDescription());
        }

        // INSERT
        else {
            entity = FlexValueSet.builder()
                    .flexValueSetCode(dto.getFlexValueSetCode())
                    .flexValueSetName(dto.getFlexValueSetName())
                    .enableFlag(dto.getEnableFlag())
                    .period(dto.getPeriod())
                    .description(dto.getDescription())
            .build();
        }

        flexValueSetRepository.save(entity);

        // DETAIL
        if (dto.getDetail() != null) {

            var listUpdateDetail = new ArrayList<FlexValue>();

            for (EditFlexValueDto d : dto.getDetail()) {

                if (Objects.equals(Enumeration.EntityState.DELETE,d.getEntityState())) continue;

                FlexValue detail;

                if (d.getFlexValueId() != null) {
                    detail = flexValueRepository.findById(d.getFlexValueId())
                            .orElse(new FlexValue());
                } else {
                    detail = new FlexValue();
                }

                detail.setFlexValueSetId(entity.getFlexValueSetId());
                detail.setFlexValue(d.getFlexValue());
                detail.setFlexValueName(d.getFlexValueName());
                detail.setDescription(d.getDescription());

                listUpdateDetail.add(detail);
//                flexValueRepository.save(detail);
            }

            if(!listUpdateDetail.isEmpty()){
                flexValueRepository.saveAll(listUpdateDetail);
            }
        }

        // DELETE LIST
        if (dto.getListDelete() != null && !dto.getListDelete().isEmpty()) {
            flexValueRepository.deleteAllById(dto.getListDelete());
        }

        return entity;
    }


//
//    public Object getByIDWithDetail(Long id) {
//        FlexValueSets master = flexValueSetRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Not found"));
//
//        List<FlexValues> detail = flexValueRepository.findBySetId(id);
//
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("master", master);
//        rs.put("detail", detail);
//
//        return rs;
//    }
//
//    public Object getById(Long id) {
//        return flexValueSetRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Not found"));
//    }
//
//    public void delete(Long id) {
//        if (!flexValueSetRepository.existsById(id)) {
//            throw new RuntimeException("Not found");
//        }
//        flexValueSetRepository.deleteById(id);
//    }
//
//    public void editLink(EditFlexHierachyDto dto) {
//
//        List<Long> listAdd = dto.getListAddId();
//        List<Long> listRemove = dto.getListRemoveId();
//        Long currentID = dto.getCurrentID();
//        Boolean addChild = dto.getAddChild();
//
//        // ADD
//        if (listAdd != null) {
//            for (Long e : listAdd) {
//
//                Long parent = addChild ? currentID : e;
//                Long child = addChild ? e : currentID;
//
//                if (!flexHierarchySetRepository.exists(parent, child)) {
//                    flexHierarchySetRepository.insert(parent, child);
//                }
//            }
//        }
//
//        // REMOVE
//        if (listRemove != null) {
//            for (Long e : listRemove) {
//
//                Long parent = addChild ? currentID : e;
//                Long child = addChild ? e : currentID;
//
//                if (flexHierarchyRepository.exists(parent, child)) {
//                    throw new RuntimeException("Đang bị sử dụng");
//                }
//
//                flexHierarchySetRepository.delete(parent, child);
//            }
//        }
//    }
//
//    public Object getPagingAllSetForLink(PagingDto query, Long id) {
//        return flexValueSetRepository.getPagingAllSetForLink(query, id);
//    }
//
//    public Object getPagingAllChildLinkSet(PagingDto query, Long id) {
//        return flexValueSetRepository.getPagingAllChildLinkSet(query, id);
//    }
//
//    public Object getPagingAllParentLinkSet(PagingDto query, Long id) {
//        return flexValueSetRepository.getPagingAllParentLinkSet(query, id);
//    }
//
//    public Object getAllFlexValueLink(Long id, Boolean isChild) {
//        return isChild
//                ? flexValueRepository.getHierarchyChild(id)
//                : flexValueRepository.getHierarchyParent(id);
//    }
//
//    public void editLinkValue(EditFlexHierachyDto dto) {
//        // giống editLink nhưng dùng FlexHierarchy (value level)
//        // giữ nguyên logic như NestJS
//    }
}