package com.lnk.marts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class OrderItems implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Integer quantity;
	
	@Column
	private float price;
	
	@OneToOne
	Product product;
	
	@ManyToOne
	Order order;
	
	private String recordStatus;
}
