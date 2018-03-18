package com.skip.orderapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.skip.orderapi.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
