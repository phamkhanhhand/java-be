package com.phamkhanhhand.kap.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class FeignClientConfig {


    //add token to feign
    @Bean
    public RequestInterceptor requestInterceptor() {
        return (RequestTemplate template) -> {

                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();
                    String bearerToken = request.getHeader("Authorization");
                    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                        String token = bearerToken.substring(7);
                        template.header("Authorization", "Bearer " + token);
                    }
                }
            };


//
//            var auth = SecurityContextHolder.getContext().getAuthentication();
//            if (auth != null && auth.getCredentials() instanceof Jwt jwt) {
//                template.header("Authorization", "Bearer " + jwt.getTokenValue());
//            }
//        };
    }

//
//    @Bean
//    public Request.Options requestOptions() {
//        // connectTimeout, readTimeout, followRedirects
//        return new Request.Options(2, 5);
//    }

}
