package com.skip.orderapi.service;

import java.util.List;

import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.model.Product;
import com.skip.orderapi.repository.ProductRepository;

@Component
public class ProductService {
	@Autowired private ProductRepository productRepo;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	public Iterable<Product> listAll(){
		return productRepo.findAll();
	}
	
	public List<Product> search(@Header("searchText") String searchText){
		return productRepo.searchProductByName(searchText.toLowerCase());
	}
	
	public Product findById(@Header("productId") Long productId){
		return productRepo.findOne(productId);
	}
}
