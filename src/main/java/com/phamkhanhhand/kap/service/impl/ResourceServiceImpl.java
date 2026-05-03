package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.common.JwtUtil;
import com.phamkhanhhand.kap.dto.LoginDTO;
import com.phamkhanhhand.kap.dto.TokenResponseDTO;
import com.phamkhanhhand.kap.model.AuthAccounts;
import com.phamkhanhhand.kap.repository.AuthAccountReponsitory;
import com.phamkhanhhand.kap.repository.ResourceReponsitory;
import com.phamkhanhhand.kap.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceReponsitory resourceReponsitory;


    @Override
    public Object getMenuByLoginUser()
    {
       var rs = resourceReponsitory.findAll();


       return  rs;
    }


}
