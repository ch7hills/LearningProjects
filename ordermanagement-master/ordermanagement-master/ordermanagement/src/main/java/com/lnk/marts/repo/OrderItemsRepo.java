package com.lnk.marts.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.lnk.marts.domain.Order;
import com.lnk.marts.domain.OrderItems;

public interface OrderItemsRepo extends CrudRepository<OrderItems, Integer>{

	List<OrderItems> findByOrder(Order order);
}
