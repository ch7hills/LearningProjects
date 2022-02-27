package com.java.ds;

public class QueueDemo {
	
	public static void main(String args[]) throws Exception {
		Queue queue= new Queue(10);
		String input = "welcome";
		for(int i=0;i<input.length();i++) {
			queue.insert(input.charAt(i));
		}
		
		while(!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}

}
