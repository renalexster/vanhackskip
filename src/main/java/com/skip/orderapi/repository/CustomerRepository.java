package com.skip.orderapi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skip.orderapi.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	@Query(value="select c from Customer as c where email=:email and password=:password")
	public Customer authCustomer(@Param("email") String email, @Param("password") String password);
}
