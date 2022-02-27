package com.pavan.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import com.pavan.demo.models.Employee;
 
public class Java8Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<Employee> employeeList = Arrays.asList(
				new Employee(1,"abc1",90000, "Dev"),
				new Employee(2,"abc2",80000, "Dev"),
				new Employee(3,"abc3",70000, "Dev"),
				new Employee(4,"abc4",60000, "Dev"),
				new Employee(5,"abc5",50000, "SAAS"),
				new Employee(6,"abc6",40000, "SAAS"),
				new Employee(7,"abc7",30000, "SAAS"),
				new Employee(8,"abc8",20000, "SAAS"),
				new Employee(9,"abc9",10000, "SAAS"),
				new Employee(10,"abc10",9000, "HR"),
				new Employee(11,"abc11",8000, "HR"),
				new Employee(12,"abc12",7000, "HR"),
				new Employee(13,"abc13",6000, "Test"),
				new Employee(14,"abc14",5000, "Test"),
				new Employee(15,"abc15",4000, "Test")
		);
		
		Comparator<Employee> employeeComparing = Comparator.comparing(Employee::getSalary);
		List<Employee> res = employeeList.stream().sorted(employeeComparing).collect(Collectors.toList());
		System.out.println(res);
		
		Optional<Employee> secondHighestSalary = employeeList.stream().sorted(employeeComparing.reversed()).skip(1).findFirst();
		System.out.println(secondHighestSalary);
		
		List<Employee> res2 = employeeList.stream().sorted(employeeComparing).skip(5).collect(Collectors.toList());
		System.out.println(res2);
		
		List<Employee> res3 = employeeList.stream().sorted(employeeComparing).limit(5).collect(Collectors.toList());
		System.out.println(res3);
		
		//List<Employee> res4 = employeeList.stream().allMatch(e -> e.getSalary() > 10000);
		System.out.println(employeeList.stream().allMatch(e -> e.getSalary() > 10000));
		System.out.println(employeeList.stream().anyMatch(e -> e.getSalary() > 10000));
		System.out.println(employeeList.stream().noneMatch(e -> e.getSalary() > 10000));
		
		List<Employee> res5 = employeeList.stream().sorted(employeeComparing).limit(5).collect(Collectors.toList());
		System.out.println(res5);
		
		double sum = employeeList.stream().mapToDouble(Employee::getSalary).sum();
		System.out.println(sum);
		
		OptionalDouble avg = employeeList.stream().mapToDouble(Employee::getSalary).average();
		System.out.println(avg); 
		
		Employee max = employeeList.stream().max(Comparator.comparing(Employee::getSalary)).get();
		System.out.println(max); 
		
		long count = employeeList.stream().mapToDouble(Employee::getSalary).count();
		System.out.println(count); 
		
		System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDept))); 
		
		System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.maxBy(employeeComparing)))); 
		
		System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.counting()))); 
		
		System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDept, Collectors.mapping(Employee::getName,Collectors.toList())))); 
	
		//String test = "Spring boot programmer, jdbc, j2ee, kafka, redis doot is best Sprink";
		String content = new String(Files.readAllBytes(Paths.get("E:\\Practice\\Java8Demo\\Test.txt")));
		System.out.println(content);
		List<String> words =  Arrays.asList(content.split(" "));
		Comparator<String> employeeComparing2 = Comparator.comparing(String::toString);
		Comparator<String> employeeComparing1 = Comparator.comparing(String::length).reversed();		
		words.stream().sorted(employeeComparing1.thenComparing(employeeComparing2)).forEach(System.out::println);

	}

}
