package com.lnk.marts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class ProductCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100)
	private String name;
	
	@Column
	private Status status;
	
	@Column
	private RecordStatus recordStatus;
	
	@ManyToOne
	private Product product;

}
