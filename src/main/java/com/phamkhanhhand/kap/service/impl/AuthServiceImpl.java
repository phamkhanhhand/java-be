package com.phamkhanhhand.kap.service.impl;

import com.phamkhanhhand.kap.common.Enumeration;
import com.phamkhanhhand.kap.common.JwtUtil;
import com.phamkhanhhand.kap.common.RequestHeaderUtil;
import com.phamkhanhhand.kap.common.UserContextUtil;
import com.phamkhanhhand.kap.dto.*;
import com.phamkhanhhand.kap.dto.mapper.AdjustmentMapper;
import com.phamkhanhhand.kap.feign.AdminFeign;
import com.phamkhanhhand.kap.model.Adjustment;
import com.phamkhanhhand.kap.model.AdjustmentDetail;
import com.phamkhanhhand.kap.model.AuthAccounts;
import com.phamkhanhhand.kap.model.Scope;
import com.phamkhanhhand.kap.repository.AdjustmentDetailReponsitory;
import com.phamkhanhhand.kap.repository.AdjustmentReponsitory;
import com.phamkhanhhand.kap.repository.AuthAccountReponsitory;
import com.phamkhanhhand.kap.repository.impl.AdjustmentRepositoryImpl;
import com.phamkhanhhand.kap.security.DataUserContext;
import com.phamkhanhhand.kap.service.AdjustmentHistoryService;
import com.phamkhanhhand.kap.service.AdjustmentService;
import com.phamkhanhhand.kap.service.AuthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
//
//    private final AdjustmentMapper adjustmentMapper;
//    private final AdjustmentReponsitory adjustmentReponsitory;
//    private final AdjustmentRepositoryImpl adjustmentRepositoryImpl;
//

    private final AuthAccountReponsitory  userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;


    @Override
    public TokenResponseDTO login(LoginDTO req)
    {
        TokenResponseDTO rs = TokenResponseDTO.builder().build();
        AuthAccounts user = userRepository.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Sai mật khẩu");
        }


        // 👉 tạo token
        var token = jwtUtil.generateToken(user.getUsername());
        rs.setAccess_token(token);
        return rs;
    }

    @Override
    public String register(LoginDTO req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("User đã tồn tại");
        }

        AuthAccounts user = new AuthAccounts();
        user.setUsername(req.getUsername());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));

        userRepository.save(user);

        return "Register OK";
    }

}
