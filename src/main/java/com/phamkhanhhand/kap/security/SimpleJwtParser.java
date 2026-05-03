package com.phamkhanhhand.kap.security;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Map;

public class SimpleJwtParser {

    /**
     * Trích xuất payload từ JWT mà không cần xác thực
     */
    public static Map<String, Object> getPayload(String token) {
        try {
            String[] parts = token.split("\\.");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Token không hợp lệ");
            }

            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(payloadJson, Map.class);

        } catch (Exception e) {
            throw new RuntimeException("Không thể phân tích token", e);
        }
    }

    /**
     * Lấy username từ payload ('sub' là trường thường dùng để lưu username)
     */
    public static String getUsername(String token) {
        Map<String, Object> payload = getPayload(token);
        Object sub = payload.get("sub");
        return sub != null ? sub.toString() : null;
    }
}
