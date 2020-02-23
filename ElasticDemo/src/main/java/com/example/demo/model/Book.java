package com.example.demo.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "elasticdemo", type = "books")
public class Book {
Long Id;
String Author;
Double Price;
public Long getId() {
	return Id;
}
public void setId(Long id) {
	Id = id;
}
public String getAuthor() {
	return Author;
}
public void setAuthor(String author) {
	Author = author;
}
public Double getPrice() {
	return Price;
}
public void setPrice(Double price) {
	Price = price;
}
public Book(Long id, String author, Double price) {
	super();
	Id = id;
	Author = author;
	Price = price;
}
public Book() {
	
}


}
