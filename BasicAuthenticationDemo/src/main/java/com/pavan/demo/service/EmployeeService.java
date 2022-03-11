package com.pavan.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pavan.demo.entity.EmployeeEntity;
import com.pavan.demo.repositories.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private EmployeeRepository empRepo;
	
	public EmployeeEntity createEmployee(EmployeeEntity employee) {
		return empRepo.save(employee);
		//return employeeList.get(0);
	}
	
	public EmployeeEntity getEmployeeById(Long empId) {
		return empRepo.findById(empId).orElse(new EmployeeEntity());
		//return employeeList.get(0);
	}
	
	public List<EmployeeEntity> getAllEmployee() {
		return empRepo.findAll();
	}
	
	public ResponseEntity<List> getList(){
		HttpHeaders hdr= new HttpHeaders();
		hdr.set("Authorization", "Basic N2hpbGxzOjdoaWxscw==");
		HttpEntity<Void> requestEntity = new HttpEntity<>(hdr);
		return restTemplate.exchange("http://localhost:2022/employee/getAll", HttpMethod.GET, requestEntity, List.class);
	}
	
}
