package com.java.ds;

public class Queue {
	int max;
	int front;
	int rear;
	int nitems;
	char[] queueArray;
	
	public Queue(int size) {
		max= size;
		front= 0;
		rear= -1;
		nitems= 0;
		queueArray = new char[size];
	}
	
	public boolean isFull() {
		return nitems == max;
	}
	
	public boolean isEmpty() {
		return nitems == 0;
	}
	
	public void insert(char item) throws Exception {
		if(!isFull()) {
			if(rear == (max-1)) {
				rear = -1;
			}
			queueArray[++rear]=item;
			nitems++;
		}else {
			System.out.println("Queue is full");
			throw new Exception("Queue is full");
		}
	}
	
	public char remove() throws Exception {
		if(!isEmpty()) {
			if(front == max) {
				front =0;
			}
			nitems--;
			char temp = queueArray[front];	
			front++;
			return temp;
		}else {
			System.out.println("Queue is empty");
			throw new Exception("Queue is empty");
		}
	}

}
