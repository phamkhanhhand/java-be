package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.EditFlexValueSetDto;
import com.phamkhanhhand.kap.dto.PageParamDTO;
import com.phamkhanhhand.kap.service.FlexValueSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/flex-value-set")
@RequiredArgsConstructor
public class FlexValueSetController {

    private final String headMapping = "/api/flex-value-set";

    private final FlexValueSetService flexValueSetService;

    @GetMapping("search")
//    @CheckPermission(uri = headMapping +"/search", scopes = {Enumeration.Scopes.ADD, Enumeration.Scopes.EDIT})
    public Object getPagingFlexValueSet( PageParamDTO query) {
        String username = UserContextUtil.getCurrentUsername();
        return flexValueSetService.getPagingFlexValueSet(query);
    }


    @PostMapping("/save")
    public Object save(@RequestBody EditFlexValueSetDto dto) {
        return flexValueSetService.save(dto);
    }

//
//    @GetMapping("/{id}")
//    public Object getById(@PathVariable Long id) {
//        return flexValueSetService.getById(id);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable Long id) {
//        flexValueSetService.delete(id);
//    }
//
//    @GetMapping("/detail/{id}")
//    public Object getByIdWithDetail(@PathVariable Long id) {
//        return flexValueSetService.getByIDWithDetail(id);
//    }
//
//    @GetMapping("/getPagingAllForLink/{id}")
//    public Object getPagingAllSetForLink(  PageParamDTO query,
//                                         @PathVariable Long id) {
//        return flexValueSetService.getPagingAllSetForLink(query, id);
//    }
//
//    @GetMapping("/getPagingAllChildLinkSet/{id}")
//    public Object getPagingAllChildLinkSet( PageParamDTO query,
//                                           @PathVariable Long id) {
//        return flexValueSetService.getPagingAllChildLinkSet(query, id);
//    }
//
//    @GetMapping("/getPagingAllParentLinkSet/{id}")
//    public Object getPagingAllParentLinkSet( PageParamDTO query,
//                                            @PathVariable Long id) {
//        return flexValueSetService.getPagingAllParentLinkSet(query, id);
//    }
//
//    @PostMapping("/editLink")
//    public void editLink(@RequestBody EditFlexHierachyDto dto) {
//        flexValueSetService.editLink(dto);
//    }
//
//    @GetMapping("/getAllFlexValueLink/{id}")
//    public Object getAllFlexValueLink(@PathVariable Long id,
//                                      @RequestParam Boolean isChild) {
//        return flexValueSetService.getAllFlexValueLink(id, isChild);
//    }
//
//    @PostMapping("/editLinkValue")
//    public void editLinkValue(@RequestBody EditFlexHierachyDto dto) {
//        flexValueSetService.editLinkValue(dto);
//    }
}
