package com.phamkhanhhand.kap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//quan trọng để quét
@SpringBootApplication(scanBasePackages = "com.phamkhanhhand.kap")
@EnableJpaRepositories("com.phamkhanhhand.kap.repository")  // Chỉ định gói chứa các repository

@EnableFeignClients(basePackages = "com.phamkhanhhand.kap.feign")
@EntityScan("com.phamkhanhhand.kap.model")  // Đảm bảo quét entity
//@SpringBootApplication
public class KapApplication {

	public static void main(String[] args) {
		SpringApplication.run(KapApplication.class, args);
	}

}
