package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients("com.example.demo.service")
public class FeigndemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeigndemoApplication.class, args);
	}

}
