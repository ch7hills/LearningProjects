package com.java.ds;

public class Stack {
	private int max;
	private int top;
	private char[] stackArray;
	
	public Stack(int max) {
		top=-1;
		stackArray = new char[max];
		this.max=max;
	}
	
	public boolean isFull() {
		return top == (max-1);
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	public void push(char ch) throws Exception {
		if(!isFull()) {
			top++;
			stackArray[top]=ch;
		}else {
			System.out.println("Stack is already full");
			throw new Exception("Stack is already full");
		}
	}
	
	public char pop() throws Exception {
		if(!isEmpty()) {
			return stackArray[top--];
		}else {
			System.out.println("Stack is already empty");
			throw new Exception("Stack is already empty");
		}
	}

	public char peak() {
		return stackArray[top];	
	}
}
