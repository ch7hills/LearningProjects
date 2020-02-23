package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.esrepo.ElasticRepo;
import com.example.demo.model.Book;

@Service
public class ESDemoService {
	@Autowired
	ElasticRepo esRepo;
	
	public String updateBook(Book b) {
		/*
		 * b.setId(1); b.setAuthor("abc"); b.setPrice(1000);
		 */
		esRepo.save(b);
		return "success";
	}

}
