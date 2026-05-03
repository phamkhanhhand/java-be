package com.phamkhanhhand.kap.service;

import com.phamkhanhhand.kap.dto.LoginDTO;
import com.phamkhanhhand.kap.dto.TokenResponseDTO;


public interface AuthService {

    public TokenResponseDTO login(LoginDTO loginDTO);

    public String register(LoginDTO req);

}
