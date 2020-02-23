package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

	@Configuration
	public class KafkaConsumerConfig {

		@Value("${go.kafka.cluser.address}")
		private String cluserAddress;

		@Value("${go.kafka.consumer.group-id}")
		private String groupId;

		@Bean
		public ConsumerFactory<String, String> consumerFactory() {
			Map<String, Object> props = new HashMap<>();
			props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, cluserAddress);
			props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
			props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
			return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
					new StringDeserializer());
		}

		@Bean
		@ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
		public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
			ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
			factory.setConsumerFactory(consumerFactory());
			return factory;
		}

}
