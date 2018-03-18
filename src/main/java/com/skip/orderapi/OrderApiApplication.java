package com.skip.orderapi;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.skip.orderapi.model.Cousine;
import com.skip.orderapi.model.Customer;
import com.skip.orderapi.model.Order;
import com.skip.orderapi.model.OrderItem;
import com.skip.orderapi.model.Product;
import com.skip.orderapi.model.Store;
import com.skip.orderapi.repository.CousineRepository;
import com.skip.orderapi.repository.CustomerRepository;
import com.skip.orderapi.repository.OrderRepository;
import com.skip.orderapi.repository.ProductRepository;
import com.skip.orderapi.repository.StoreRepository;

@SpringBootApplication
public class OrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApiApplication.class, args);
	}
	
	@Autowired CustomerRepository customeRepo;
	@Autowired CousineRepository cousineRepo;
	@Autowired StoreRepository storeRepo;
	@Autowired ProductRepository productRepo;
	@Autowired OrderRepository orderRepo;
	
	@Bean
	@Transactional
	public CommandLineRunner runner() {
	    return args -> {
	        Customer c1 = new Customer();
	        c1.setName("Renato Barros");
	        c1.setEmail("renalexster@gmail.com");
	        c1.setPassword("123456");
	       
	        customeRepo.save(c1);
	        
	        cousineRepo.save(Arrays.asList(new Cousine("Pizza")
	        		, new Cousine("Chinese")
	        		, new Cousine("Vietnamese")
	        		, new Cousine("Sushi")
	        		));
	        
	        Cousine fix = new Cousine();
	        fix.setId(1l);
	        
	        Store fixStoreOne = new Store();
	        fixStoreOne.setId(1l);;
	        Store fixStoreTwo = new Store();
	        fixStoreTwo.setId(2l);;
	        
	        
	        storeRepo.save(Arrays.asList(
	        		new Store("Hai Shang", "2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada", fix)
	        		,new Store("Ye's", "616 St James St, Winnipeg, Manitoba R3G 3J5, Canada", fix)
	        		,new Store("Good Earth Chop Suey House", "1849 Portage Ave, Winnipeg, Manitoba R3J 0G8, Canada", fix)
	        		));
	        
	        
	        productRepo.save(Arrays.asList(
	        		new Product(fixStoreOne, "Shrimp Tempura", "Fresh shrimp battered and deep fried until golden brown", 10.95d)
	        		,new Product(fixStoreOne, "Shrimp with Snow Peas and Cashew", "A delicious combination of fresh shrimp, snow peas, and cashew", 12.5d)
	        		,new Product(fixStoreOne, "Special Deep-Fried Fish", "Tilapia fish deep fried until flaky and tender", 12.95d)
	        		,new Product(fixStoreTwo, "BBQ Pork Egg Foo Yung", "Chinese omelette filled with barbequed pork", 10.95d)
	        		));
	        
	        Product fixProductOne = new Product();
	        fixProductOne.setId(1l);
	        
	        Order order = new Order();
	        order.setContact("Illa from Vanck");
	        order.setCustomer(c1);
	        order.setDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	        order.setDeliveryAddress("Hotel Transamerica");
	        order.setLastUpdate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
	        order.setStatus("OK");
	        order.setStoreId(fixStoreOne.getId());
	        order.setTotal(12.32d);
	        
	        OrderItem orderItemOne = new OrderItem();
	        orderItemOne.setOrder(order);
	        orderItemOne.setPrice(12.32d);
	        orderItemOne.setProduct(fixProductOne);
	        orderItemOne.setQuantity(1);
	        orderItemOne.setTotal(12.32d);
	        
	        order.setListOrderItem(Arrays.asList(orderItemOne));
	        
	        orderRepo.save(order);
	        
	    };
	}
}
