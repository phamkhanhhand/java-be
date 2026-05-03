package com.phamkhanhhand.kap.controller;

import com.phamkhanhhand.kap.dto.LoginDTO;
import com.phamkhanhhand.kap.dto.TokenResponseDTO;
import com.phamkhanhhand.kap.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final String headMapping = "/api/auth";
    private final AuthService authService;



    @PostMapping("login")
//    @CheckPermission(uri = headMapping +"/login", scopes = {Enumeration.Scopes.ADD, Enumeration.Scopes.EDIT})
    public TokenResponseDTO login(@RequestBody LoginDTO requestDTO)
    {
        return authService.login(requestDTO);
    }



    @PostMapping("register")
//    @CheckPermission(uri = headMapping +"/register", scopes = {Enumeration.Scopes.ADD, Enumeration.Scopes.EDIT})
    public String register(@RequestBody LoginDTO requestDTO)
    {
        return authService.register(requestDTO);
    }

}
