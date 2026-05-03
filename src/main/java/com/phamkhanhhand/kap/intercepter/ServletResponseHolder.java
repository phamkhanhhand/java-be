package com.phamkhanhhand.kap.intercepter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;


/*
* add wrap to response
 */
@Component
public class ServletResponseHolder implements Filter {

    public static final ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            responseHolder.set((HttpServletResponse) response);
            chain.doFilter(request, response);
        } finally {
            responseHolder.remove(); // Đảm bảo tránh memory leak
        }
    }

    public static HttpServletResponse getResponse() {
        return responseHolder.get();
    }
}
