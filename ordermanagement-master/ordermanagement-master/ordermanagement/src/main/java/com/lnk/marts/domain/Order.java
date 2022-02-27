package com.lnk.marts.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Order implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column 
	private LocalDate orderDate;
	
	@Column(length=2000)
	private String shipToAddress;
	
	@Column
	private LocalDate shipToDate;
	
	@Column
	private String orderStatus;
		
	@Column
	private Float orderAmount;
	
	@Column
	private Integer recordStatus;

	@ManyToOne()
	Customer customer;

	@OneToMany(mappedBy="order")
	private List<OrderItems> orderItems;
	

}
