package com.java;

public class printTables {
	public  static void printTable(int n){
		for(int i=1;i<11;i++) {
			System.out.println(String.format("%d * %d = %d", n,i,n*i));
		}
	}
	
	public static void main(String[] args) {
		printTable(3); 
	}
}
