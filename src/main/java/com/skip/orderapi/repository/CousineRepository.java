package com.skip.orderapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.skip.orderapi.model.Cousine;
import com.skip.orderapi.model.Store;

public interface CousineRepository extends CrudRepository<Cousine, Long> {
	@Query("Select c from Cousine c where LOWER(c.name) like :name%")
	public List<Cousine> searchCousineByName(@Param("name") String name);
	
	@Query("Select s from Store s where s.cousine.id = :cousineId")
	public List<Store> searchStoreByCousineId(@Param("cousineId") Long cousineId);
}
