package com.skip.orderapi.service;

import java.util.List;

import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.model.Cousine;
import com.skip.orderapi.model.Store;
import com.skip.orderapi.repository.CousineRepository;

@Component
public class CousineService {
	@Autowired private CousineRepository cousineRepo;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public List<Cousine> search(@Header("searchText") String searchText){
		return cousineRepo.searchCousineByName(searchText.toLowerCase());
	}
	public List<Store> searchStore(@Header("cousineId") Long cousineId){
		return cousineRepo.searchStoreByCousineId(cousineId);
	}
}
