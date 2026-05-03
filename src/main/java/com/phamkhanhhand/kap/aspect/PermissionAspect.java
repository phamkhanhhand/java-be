package com.phamkhanhhand.kap.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/*
* Aspect: check authorization,...
* phamkhanhhand & chatgpt
 */
@Aspect
@Component
@Slf4j
public class PermissionAspect {

    @Before("@annotation(aspect.com.phamkhanhhand.kap.CheckPermission)")
    public void checkPermission(JoinPoint joinPoint) throws Throwable {
        // get annotation, Lấy annotation
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CheckPermission annotation = method.getAnnotation(CheckPermission.class);

        String uri = annotation.uri();
        List<String> requiredScopes = Arrays.asList(annotation.scopes());

        // Lấy HTTP request hiện tại
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new RuntimeException("No request context available");
        }

        HttpServletRequest request = attributes.getRequest();

        // Lấy sourceCode từ header
        String sourceCode = request.getHeader("sourceCode");

        if (sourceCode == null || sourceCode.isEmpty()) {
            throw new RuntimeException("Missing sourceCode in headers");
        }

        if (!hasPermission(sourceCode, uri, requiredScopes)) {
            throw new SecurityException("Access denied for sourceCode: " + sourceCode);
        }

//        log.info("Access granted for sourceCode: {}, uri: {}, scopes: {}", sourceCode, uri, requiredScopes);
    }

    // TODO Hàm kiểm tra quyền (mock)
    private boolean hasPermission(String sourceCode, String uri, List<String> scopes) {

        return true;
    }
}
