package com.skip.orderapi;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.skip.orderapi.model.Customer;
import com.skip.orderapi.repository.CustomerRepository;

@SpringBootApplication
public class OrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiApplication.class, args);
	}
	
	@Autowired CustomerRepository customerepo;
	
	@Bean
	@Transactional
	public CommandLineRunner runner() {
	    return args -> {
	        Customer c1 = new Customer();
	        c1.setName("Renato");
	        
	        customerepo.save(c1);
	    };
	}
}
