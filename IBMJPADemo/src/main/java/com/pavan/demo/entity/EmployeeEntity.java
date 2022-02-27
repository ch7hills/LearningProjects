package com.pavan.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeeEntity {
	@Id
	@GeneratedValue
	Long id;
	String name;
	Long salary;
	public EmployeeEntity(){
		
	}
	public EmployeeEntity(long ld, String name, long sal) {
		this.id=id;
		this.name =name;
		this.salary = sal;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	

}
