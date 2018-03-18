package com.skip.orderapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.skip.orderapi.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
}
