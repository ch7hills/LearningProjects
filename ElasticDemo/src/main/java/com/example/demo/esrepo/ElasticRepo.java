package com.example.demo.esrepo;


import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.demo.model.Book;

public interface ElasticRepo extends ElasticsearchRepository<Book,String>{
	 Page<Book> findByAuthor(String author, Pageable pageable);

	 List<Book> findByTitle(String title);
}
