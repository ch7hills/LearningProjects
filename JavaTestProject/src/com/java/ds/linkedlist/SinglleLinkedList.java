package com.java.ds.linkedlist;

public class SinglleLinkedList {
	private Node first;
	private Node last;
	public SinglleLinkedList() {
		
	}
	public boolean isEmpty() {
		return first ==null;
	}
	
	public void insertFirst(int data) {
		Node newNode = new Node();
		newNode.data = data;
		newNode.next = first;
		first = newNode;
		
	}
	
	public Node deleteFirst() {
		Node temp = first;
		first = first.next;
		return temp;
	}
	
	public void displayList() {
		getLastNode();
		System.out.println("##### start ######");
		first.display();
		last.display();
		System.out.println("##### stop ######");
		Node currentNode = first;
		while(currentNode!=null) {
			currentNode.display();
			currentNode =  currentNode.next;
		}
	}
	
	public void insertLast(int data) {
		getLastNode();
		Node newNode = new Node();
		newNode.data=data;
		last.next = newNode;
		last=newNode;
	}
	
	public Node getLastNode() {
		if(last==null) {
			Node currentNode = first;
			while(currentNode.next!=null) {//still for last node
				currentNode =currentNode.next;
			}
			last = currentNode;
		}
		return last;
	}
	
}
