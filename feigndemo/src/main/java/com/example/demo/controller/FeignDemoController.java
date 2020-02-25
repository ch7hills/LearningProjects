package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ClientPropertyBean;
import com.example.demo.service.ClientFeignService;

@RestController
public class FeignDemoController {
	@Autowired
	ClientFeignService proxy;
	
	@Autowired
	Environment env;
	
	@GetMapping("/getFeignProperty")
	public ClientPropertyBean getProperty() {
		return proxy.getProperty();
	}
}
