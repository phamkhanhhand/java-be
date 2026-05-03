package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.dto.LoginDTO;
import com.phamkhanhhand.kap.dto.TokenResponseDTO;
import com.phamkhanhhand.kap.service.AuthService;
import com.phamkhanhhand.kap.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/menu")
public class MenuController {

    private final String headMapping = "/api/menu";
    private final AuthService authService;
    private final ResourceService resourceService;



    @GetMapping("get-menu")
//    @CheckPermission(uri = headMapping +"/login", scopes = {Enumeration.Scopes.ADD, Enumeration.Scopes.EDIT})
    public Object getMenu()
    {
        return resourceService.getMenuByLoginUser();
    }



}
