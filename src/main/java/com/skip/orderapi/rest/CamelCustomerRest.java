package com.skip.orderapi.rest;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.skip.orderapi.dto.AuthDTO;
import com.skip.orderapi.exception.AuthException;
import com.skip.orderapi.model.Customer;
import com.skip.orderapi.repository.Facade;
import com.skip.orderapi.utils.ErrorHandleRouter;
import com.skip.orderapi.utils.Utils;

@Component
public class CamelCustomerRest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(AuthException.class)
		.handled(true)
		.log(LoggingLevel.ERROR, "Error on auth")
		.bean(ErrorHandleRouter.class, "error")
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader(Exchange.HTTP_RESPONSE_CODE).constant("500")
		.markRollbackOnly();
		
		rest("/customer")
		.get().produces("application/json").route()
		.log("LIST Customer updated...")
		.bean(Facade.class, "listAllCustomers")
		.bean(Utils.class, "debug")			
		.endRest()
		
		.post().produces("plain/text").consumes("application/json").type(Customer.class).route()
		.log("Inserting Customer...")
		.bean(Utils.class, "debug")			
		.bean(Facade.class, "insertNewCustomer").endRest()
		
		.post("/auth").produces("plain/text").consumes("application/json").type(AuthDTO.class).route()
		.log("Inserting Customer...")
		.bean(Utils.class, "debug")			
		.bean(Facade.class, "authCustomer")
		
		;
	}

}
