package com.skip.orderapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skip.orderapi.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	
	@Query("select o from Order o join fetch o.listOrderItem where o.id=:orderId")
	public Order findOrderById(@Param("orderId")Long orderId);
}
