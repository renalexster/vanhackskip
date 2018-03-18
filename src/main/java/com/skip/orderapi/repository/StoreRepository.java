package com.skip.orderapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.skip.orderapi.model.Store;

public interface StoreRepository extends CrudRepository<Store, Long> {

}
