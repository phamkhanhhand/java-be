package com.phamkhanhhand.kap.common;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class RequestHeaderUtil {

    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            return servletRequestAttributes.getRequest();
        }
        throw new IllegalStateException("Cannot get HttpServletRequest outside of HTTP context");
    }

    public static String getHeader(String name) {
        HttpServletRequest request = getCurrentHttpRequest();
        return request.getHeader(name);
    }
}