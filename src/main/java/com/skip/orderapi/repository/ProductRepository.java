package com.skip.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skip.orderapi.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("Select p from Product p where LOWER(p.name) like :name%")
	public List<Product> searchProductByName(@Param("name") String name);

}
