package test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class TestString {
	public static void main(String[] args) {
		String test = "Spring boot programmer, jdbc, j2ee, kafka, redis doot is best Sprink";
		List<String> words =  Arrays.asList(test.split(" "));
		Comparator<String> employeeComparing2 = Comparator.comparing(String::toString);
		Comparator<String> employeeComparing1 = Comparator.comparing(String::length).reversed();		
		words.stream().sorted(employeeComparing1.thenComparing(employeeComparing2)).forEach(System.out::println);
	}
}
