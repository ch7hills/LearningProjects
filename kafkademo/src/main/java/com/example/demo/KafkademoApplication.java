package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.producers.KafkaProducers;

@SpringBootApplication
public class KafkademoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KafkademoApplication.class, args);
		
	}

}
