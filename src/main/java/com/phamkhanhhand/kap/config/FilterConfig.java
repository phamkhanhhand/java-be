//package com.phamkhanhhand.kap.config;
//import com.phamkhanhhand.kap.security.JwtTokenInspectorFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
////if not use spring-boot-starter-security
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<JwtTokenInspectorFilter> jwtFilter() {
//        FilterRegistrationBean<JwtTokenInspectorFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new JwtTokenInspectorFilter());
//        registrationBean.addUrlPatterns("/api/*"); // áp dụng cho endpoint cụ thể
//        return registrationBean;
//    }
//}
