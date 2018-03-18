package com.skip.orderapi.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.skip.orderapi.model.Customer;
import com.skip.orderapi.repository.Facade;
import com.skip.orderapi.utils.Utils;

@Component
public class CamelCustomerRest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/customer")
		.get().produces("application/json").route()
		.log("LIST Customer...")
		.bean(Facade.class, "listAllCustomers")
		.bean(Utils.class, "debug")			
		.endRest()
		
		.post().produces("plain/text").consumes("application/json").type(Customer.class).route()
		.log("Inserting Customer...")
		.bean(Utils.class, "debug")			
		.bean(Facade.class, "insertNewCustomer")
		
		;
	}

}
