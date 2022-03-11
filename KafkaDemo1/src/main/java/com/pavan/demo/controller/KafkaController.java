package com.pavan.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.pavan.demo.service.ProducerService;

@RestController
public final class KafkaController {

    private final ProducerService producerService;
   
    @Autowired
    public KafkaController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/publish/{message}")
    public String sendMessageToKafkaTopic(@PathVariable("message") String message) {
    	for(int i=1;i<100;i++)
    		producerService.sendMessage(message+i);
    	return "Success";
    }
    
    @GetMapping("/consume/{message}")
    public ResponseEntity<String>  consumeMessageToKafkaTopic(@PathVariable("message") String message) {
    	RestTemplate restTemplate = new RestTemplate();
    	ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:2021/publish/"+message, String.class);
    	System.out.println(" Consume rest via rest template result"+result);
    	return result;
    }
}