package com.pavan.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public final class ProducerService {
	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final String TOPIC = "kafkaTopic";
	
	@Autowired
	public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		logger.info(String.format("$$$$ => Producing message: %s", message));

		this.kafkaTemplate.send(TOPIC, message);
		
	}
}