package com.java.ds.linkedlist;

public class Node {
	int data;
	Node next;
	
	public void display() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return String.format("data=%s, next=%s", data, next);
	}
}
