package com.skip.orderapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.skip.orderapi.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
