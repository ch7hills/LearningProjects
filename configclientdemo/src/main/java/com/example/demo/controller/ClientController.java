package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.ClientPropertyBean;

@RestController
public class ClientController {
	@Autowired
	ClientPropertyBean cBean; 
	
	@GetMapping("/getProperty")
	public ClientPropertyBean test() {
		return cBean;
	}

}
