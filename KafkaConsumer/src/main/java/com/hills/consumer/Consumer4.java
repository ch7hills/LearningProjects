package com.hills.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer4 {
	
	@Value("${topic}")
	private String topic;
	
	@KafkaListener(topics="${topic}")
	public void consume(String message) {
		System.out.println("Cosumer 4:"+message);
	}

}
