package com.skip.orderapi.service;

import java.util.List;

import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.model.Product;
import com.skip.orderapi.model.Store;
import com.skip.orderapi.repository.StoreRepository;

@Component
public class StoreService {
	@Autowired private StoreRepository storeRepo;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public Iterable<Store> listAll(){
		return storeRepo.findAll();
	}
	
	public List<Product> searchProductsByStore(@Header("storeId") Long storeId){
		return storeRepo.searchProductsByStore(storeId);
	}
	public List<Store> search(@Header("searchText") String searchText){
		return storeRepo.searchStoreByName(searchText.toLowerCase());
	}
	
	public Store findById(@Header("storeId") Long productId){
		return storeRepo.findOne(productId);
	}
}
