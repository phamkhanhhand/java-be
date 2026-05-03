package com.phamkhanhhand.kap.intercepter;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // ✅ Nếu đã là response chuẩn thì return luôn (tránh bị lồng)
        if (body instanceof Map<?, ?> map && map.containsKey("status") && map.containsKey("data")) {
            return body;
        }


        // Lấy thông tin từ header request
        String transactionId = request.getHeaders().getFirst("TransactionId");
        String clientMessageId = request.getHeaders().getFirst("ClientMessageId");

        // Lấy status từ ServletResponse
        HttpServletResponse servletResponse = ServletResponseHolder.getResponse();
        int statusCode = servletResponse != null ? servletResponse.getStatus() : HttpStatus.OK.value();
        String message = HttpStatus.resolve(statusCode) != null
                ? HttpStatus.resolve(statusCode).getReasonPhrase()
                : "Unknown Status";

        // ✅ Tạo response theo dạng Map
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("status", statusCode);
        wrapper.put("message", message);
        wrapper.put("clientMessageId", clientMessageId);
        wrapper.put("data", body);

        return wrapper;
    }
}
