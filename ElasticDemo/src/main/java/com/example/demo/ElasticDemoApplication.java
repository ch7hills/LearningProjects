package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Book;
import com.example.demo.service.ESDemoService;

@SpringBootApplication
public class ElasticDemoApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ElasticDemoApplication.class, args);
		Book b = new Book();
		
		ESDemoService s= new ESDemoService();
		b.setId(new Long(1)); b.setAuthor("abc"); b.setPrice(new Double(1000));
		s.updateBook(b);
	}

}
