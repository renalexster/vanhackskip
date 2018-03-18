package com.skip.orderapi.service;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.dto.AuthDTO;
import com.skip.orderapi.exception.AuthException;
import com.skip.orderapi.model.Customer;
import com.skip.orderapi.repository.CustomerRepository;

import net.minidev.json.JSONObject;

@Component
public class CustomerService {
	@Autowired private CustomerRepository customerRepo;
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	public JSONObject authCustomer(@Body AuthDTO body) {
		Customer customer = customerRepo.authCustomer(body.getEmail(), body.getPassword());
		
		if (customer==null)
			throw new AuthException("Invalid user or password");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id", customer.getId());
		jsonObject.put("name", customer.getName());
		jsonObject.put("email", customer.getEmail());
		
		return jsonObject;
	}
	
	public void insertNewCustomer(@Body Customer body) {
		LOG.info(String.format("Persisting Customer %s", body.toString()));
		customerRepo.save(body);
	}

	public Iterable<Customer> listAllCustomers() {
		return customerRepo.findAll();
	}
}
