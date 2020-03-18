package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulDemoController {
	@GetMapping("/zulldemo")
	public String test() {
		return "welcome to zuul demo";
	}
}
