package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bean.ClientPropertyBean;

@FeignClient(name="client-service",url="localhost:7000")
//@FeignClient(name="client-service")
public interface ClientFeignService {
	@GetMapping("/getProperty")
	public ClientPropertyBean getProperty();
}
