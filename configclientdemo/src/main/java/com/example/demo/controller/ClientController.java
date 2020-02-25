package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ClientPropertyBean;

@RestController
public class ClientController {
	@Autowired
	ClientPropertyBean cBean; 
	@Autowired
	Environment env;
	
	
	@GetMapping("/getProperty")
	public ClientPropertyBean test() {
		cBean.setPort(Integer.valueOf(env.getProperty("server.port")));
		return cBean;
	}

}
