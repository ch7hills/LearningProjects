package com.example.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
public class KafkaConsumer {
	private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics="${go.kafkademo.topic}")
    public void receive(String message) {
		System.out.println("In Cosumer message"+message);
		log.info("Conumer Message -->: {}",message);
	}

}
