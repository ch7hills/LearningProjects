package com.java.ds.linkedlist;

public class CircularLinkedList {
	private Node first;
	private Node last;
	
	public CircularLinkedList() {
		first=null;
		last=null;
	}
	
	public void insertFirst(int data) {
		Node newNode = new Node();
		newNode.data=data;
		if(isEmpty()) {
			last = newNode;
		}
		newNode.next =first;
		first=newNode;
	}
	
	public void insertLast(int data) {
		Node newNode =  new Node();
		newNode.data = data;
		if(isEmpty()) {
			first = newNode;
		}
		newNode.next=last;
		last = newNode;
	}

	private boolean isEmpty() {
		return first==null;
	}
	
	public void deleteFirst() {
		if(first.next == null) {
			last = null;
		}
		
		first = first.next;
	}
	
	public void displayList() { 
		Node currentNode = first;
		while(currentNode!=null) {
			currentNode.display();
			currentNode =  currentNode.next;
		}
	}
}
