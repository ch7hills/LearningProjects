package com.java.ds.linkedlist;

public class AppDemo {
	public static void main(String args[]) {
	CircularLinkedList single =  new CircularLinkedList();
	single.insertFirst(10);
	single.insertFirst(20);
	single.insertFirst(30);
	single.insertFirst(40);
	single.insertFirst(50);
	single.displayList();
	single.deleteFirst();
	single.deleteFirst();
	single.displayList();
	
	single.insertLast(60);
	single.insertLast(70);
	single.insertLast(80);
	single.insertLast(90);
	single.insertLast(100);
	single.displayList();
	}

}
