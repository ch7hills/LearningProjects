package com.lnk.marts.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

public class Payment implements Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(precision=2)
	private float amount;
	
	@Column
	private LocalDate paymentDate;
	
	@Column
	private Status status;
	
	@Column
	private RecordStatus recordStatus;
	
	@OneToMany
	Order order;
	
	@OneToOne
	Customer customer;
	
	
}
