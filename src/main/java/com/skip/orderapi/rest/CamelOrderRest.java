package com.skip.orderapi.rest;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.skip.orderapi.exception.AuthException;
import com.skip.orderapi.service.OrderService;
import com.skip.orderapi.utils.ErrorHandleRouter;
import com.skip.orderapi.utils.JWTService;
import com.skip.orderapi.utils.Utils;

@Component
public class CamelOrderRest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		onException(AuthException.class)
		.handled(true)
		.log(LoggingLevel.ERROR, "Error on auth")
		.bean(ErrorHandleRouter.class, "error")
		.setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
		.setHeader(Exchange.HTTP_RESPONSE_CODE).constant("500")
		.markRollbackOnly();
		
		rest("/order")
		.get("/{orderId}").produces("application/json").route()
		.to("direct:checkToken")
		.log("Find Cousine ${header[orderId]}...")
		.bean(OrderService.class, "findOrderById")
		.bean(Utils.class, "debug")			
		.endRest()

		.get("/customer").produces("application/json").route()
		.log("Find Cousine...")
		.to("direct:checkToken")
		.bean(Utils.class, "debug")			
		.bean(OrderService.class, "findOrderById")
		.bean(Utils.class, "debug")			
		.endRest()
		
		.post().produces("application/json").route()
		.log("Creating order...")
		.to("direct:checkToken")
		.bean(OrderService.class, "findOrderById")
		.bean(Utils.class, "debug")			
		.endRest()
		
		
		;
		
		from("direct:checkToken")
		.setHeader("payload").body()
		.bean(JWTService.class, "parseToken").setProperty("X-JWT-Token", body())
		.setProperty("X-userid").jsonpath("$.id")
		.setProperty("X-name").jsonpath("$.name")
		.setProperty("X-email").jsonpath("$.email")
		.setBody().header("payload");
	}

}
