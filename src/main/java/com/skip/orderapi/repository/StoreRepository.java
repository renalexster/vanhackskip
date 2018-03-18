package com.skip.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skip.orderapi.model.Product;
import com.skip.orderapi.model.Store;

public interface StoreRepository extends CrudRepository<Store, Long> {

	@Query("Select s from Store s where LOWER(s.name) like :name%")
	public List<Store> searchStoreByName(@Param("name") String name);
	
	@Query("Select p from Product p where p.store.id=:storeId")
	public List<Product> searchProductsByStore(@Param("storeId") Long storeId);

}
