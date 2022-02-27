package com.lnk.marts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=200)
	private String	name;
	
	@Column(length=2000)
	private String description;

	@Column(length=2000)
	private String tags;
	
	@Column(precision=2)
	private Float price;
	
	@Column
	private RecordStatus recordStatus;
	
	@OneToMany
	ProductCategory category;
}
