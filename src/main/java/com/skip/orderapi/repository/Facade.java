package com.skip.orderapi.repository;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.dto.AuthDTO;
import com.skip.orderapi.exception.AuthException;
import com.skip.orderapi.model.Customer;

@Component
public class Facade {
	
	@Autowired private CustomerRepository customerRepo;
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	public Iterable<Customer> listAllCustomers(){
		return customerRepo.findAll();
	}
	
	public void insertNewCustomer(@Body Customer body) {
		LOG.info(String.format("Persisting Customer %s", body.toString()));
		customerRepo.save(body);
	}
	public void authCustomer(@Body AuthDTO body) {
		LOG.info(String.format("authCustomer... %s", body.toString()));
		if (customerRepo.authCustomer(body.getEmail(), body.getPassword())==null)
			throw new AuthException("Invalid user or password");
	}
}