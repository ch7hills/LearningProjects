package com.lnk.marts.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lnk.marts.domain.Customer;
import com.lnk.marts.domain.Order;

public interface OrderRepo extends CrudRepository<Order, Integer> {
	List <Order> findByCustomer(Customer customer);

}
