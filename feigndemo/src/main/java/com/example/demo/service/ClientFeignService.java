package com.example.demo.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.bean.ClientPropertyBean;

//@FeignClient(name="client-service",url="localhost:7000")
//@FeignClient(name="client-service")
@FeignClient(name="zuul-service")
@RibbonClient(name="client-service")
public interface ClientFeignService {
	//@GetMapping("/getProperty")
	@GetMapping("/client-service/getProperty")
	public ClientPropertyBean getProperty();
}
