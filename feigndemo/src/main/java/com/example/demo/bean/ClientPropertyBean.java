package com.example.demo.bean;

import org.springframework.stereotype.Component;

@Component
public class ClientPropertyBean {
	int min;
	int max;
	int port;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
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
		return "ClientPropertyBean [min=" + min + ", max=" + max + ", port=" + port + "]";
	}
	
}
