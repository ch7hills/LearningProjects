package com.lnk.marts.repo;

import org.springframework.data.repository.CrudRepository;

import com.lnk.marts.domain.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Integer> {

}
