package com.pavan.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.demo.entity.EmployeeEntity;
import com.pavan.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService empService;
	
	@PostMapping("/create")
	public ResponseEntity<EmployeeEntity> createEmployee(@RequestBody EmployeeEntity employee){
		
		EmployeeEntity enitityFromDb = empService.createEmployee(employee);
		
		return new ResponseEntity(enitityFromDb,HttpStatus.CREATED);		
	}

	@GetMapping("/{empId}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("empId") Long empId){
		
		EmployeeEntity enitityFromDb = empService.getEmployeeById(empId);
		
		return new ResponseEntity(enitityFromDb,HttpStatus.ACCEPTED);		
	}
	
	@GetMapping("/rest/getAll")
	public ResponseEntity<EmployeeEntity> getRestEmployeeById(){
		
		ResponseEntity<List> enitityFromDb = empService.getList();
		
		return new ResponseEntity(enitityFromDb,HttpStatus.ACCEPTED);		
	}
	
	@GetMapping("/getAll")
	public <T> ResponseEntity<List<T>> getAllEmployee(){
		return new ResponseEntity(empService.getAllEmployee(),HttpStatus.ACCEPTED);
	}
}
