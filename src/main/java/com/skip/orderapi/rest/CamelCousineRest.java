package com.skip.orderapi.rest;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.skip.orderapi.exception.AuthException;
import com.skip.orderapi.service.CousineService;
import com.skip.orderapi.utils.ErrorHandleRouter;
import com.skip.orderapi.utils.Utils;

@Component
public class CamelCousineRest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(AuthException.class)
		.handled(true)
		.log(LoggingLevel.ERROR, "Error on auth")
		.bean(ErrorHandleRouter.class, "error")
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader(Exchange.HTTP_RESPONSE_CODE).constant("500")
		.markRollbackOnly();
		
		rest("/cousine")
		.get().produces("application/json").route()
		.log("Find Cousine ${header[searchText]}...")
		.bean(CousineService.class, "listAll")
		.bean(Utils.class, "debug")			
		.endRest()
		
		.get("/search/{searchText}").produces("application/json").route()
		.log("Find Cousine ${header[searchText]}...")
		.bean(CousineService.class, "search")
		.bean(Utils.class, "debug")			
		.endRest()
		
		.get("/{cousineId}/stores").produces("application/json").route()
		.log("Find Cousine ${header[cousineId]}...")
		.bean(CousineService.class, "searchStore")
		.bean(Utils.class, "debug")			
		.endRest()
		
		
		;
	}

}
