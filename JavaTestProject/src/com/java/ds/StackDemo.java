package com.java.ds;

public class StackDemo {
	public static void main(String[] args) {
		Stack stack = new Stack(10);
		try {
			stack.push('a');
			stack.push('b');
			stack.push('c');
			stack.push('d');
			while(!stack.isEmpty()) {
				System.out.println(stack.pop());
			}

			System.out.println(reverse("ARUNA"));
			System.out.println(reverse("MADAM"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String reverse(String input) throws Exception {
		Stack stack = new Stack(input.length());
		for(int i=0;i<input.length();i++) {
			stack.push(input.charAt(i));
		}
		String reverse = "";
		while(!stack.isEmpty()) {
			reverse = reverse+stack.pop();
		}
		return reverse;
	}
}
