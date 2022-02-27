package com.pavan.demo.models;

public class Employee {
	int eno;
	String name;
	double salary;
	String dept;
	public Employee(int eno, String ename, int salary, String dept) {
		this.eno=eno;
		this.name=ename;
		this.salary=salary;
		this.dept = dept;
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return String.format("Employee [eno=%s, name=%s, salary=%s, dept=%s]", eno, name, salary, dept);
	}
}
