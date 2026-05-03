package com.phamkhanhhand.kap.feign;

import com.phamkhanhhand.kap.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "user-service", url = "${provider.url}", configuration = FeignClientConfig.class)
@FeignClient(name = "user-service", url = "http://localhost:5087", configuration = FeignClientConfig.class)
public interface UserClient {
    @PostMapping("/api/Auth/test")
    Object getCurrentUser(@RequestBody Object param);
}

