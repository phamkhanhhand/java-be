package com.phamkhanhhand.kap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Cho phép cookie/auth
        config.addAllowedOrigin("http://localhost:4200"); // Cho phép frontend React
        config.addAllowedHeader("*"); // Cho phép tất cả headers
        config.addAllowedMethod("*"); // Cho phép tất cả methods (GET, POST,...)
        source.registerCorsConfiguration("/**", config); // Áp dụng cho tất cả endpoint
        return new CorsFilter(source);
    }
}
