package com.example.demo.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.producers.KafkaProducers;

import redis.clients.jedis.Jedis;

@RestController
public class KafkaDemoController {
	@Autowired
	KafkaProducers kp;
	@GetMapping(value="/test",produces="application/json")
	@ResponseBody
	public String test() throws InterruptedException, ExecutionException, TimeoutException, Exception {
		System.out.print("In controller");
		/*
		 * Jedis jedis = new Jedis("localhost", 6379); jedis.auth("net!admin");
		 */
		//jedis.psubscribe(pSubm, "__key*__:expired");
		long i=1;
		while(true) {
			// KafkaProducers kp = new KafkaProducers();
			kp.postComment("Message No:"+i);
			//jedis.set(i+"", "Message No:"+i);
			if(i>1000) {
				break;
			}
			i++;
		}
		return "success";
		
	}
}
