package com.skip.orderapi.service;

import org.apache.camel.Body;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.model.Order;
import com.skip.orderapi.repository.OrderRepository;

@Component
public class OrderService {
	@Autowired private OrderRepository orderRepo;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public Order findOrderById(@Header("orderId") Long orderId){
		return orderRepo.findOrderById(orderId);
	}
	
	public Order persistOrder(@Body Order order) {
		return orderRepo.save(order);
	}
}
