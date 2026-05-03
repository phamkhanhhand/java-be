package com.phamkhanhhand.kap.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

//if not use spring-boot-starter-security
public class JwtTokenInspectorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // optional
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            System.out.println("Token found: " + jwt);
            // decode token, extract info, etc.
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // optional
    }
}
