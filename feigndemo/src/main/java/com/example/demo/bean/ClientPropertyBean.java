package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class ClientPropertyBean {
	int min;
	int max;
	
	public ClientPropertyBean(){
		
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	@Override
	public String toString() {
		return "ClientPropertyBean [min=" + min + ", max=" + max + "]";
	}
}
