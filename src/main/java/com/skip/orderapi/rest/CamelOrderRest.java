package com.skip.orderapi.rest;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.skip.orderapi.exception.AuthException;
import com.skip.orderapi.model.Order;
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
		.get("/{orderId}").produces("application/json").type(Long.class).route()
		.to("direct:checkToken")
		.log("Find Order by Id ${header[orderId]}...")
		.bean(OrderService.class, "findOrderById")
		.bean(Utils.class, "debug")			
		.endRest()

		.post().produces("application/json").type(Order.class).route()
		.log("Creating new Order with payload ${body}...")
		.to("direct:checkToken")
		.bean(Utils.class, "debug")			
		.bean(OrderService.class, "persistOrder")
		.bean(Utils.class, "debug")			
		.endRest()
		
		.get("/customer/orders").produces("application/json").route()
		.log("Getting order by customer...")
		.to("direct:checkToken")
		.log("Getting order by customer...")
		.bean(Utils.class, "debug")			
		.bean(OrderService.class, "findOrderByCustomerId")
		.bean(Utils.class, "debug")			
		.endRest()
		
		
		;
		
		from("direct:checkToken")
		.setHeader("payload").body()
		.bean(JWTService.class, "parseToken").setProperty("X-JWT-Token", body())
		.setProperty("X-customerId").jsonpath("$.id")
		.setProperty("X-name").jsonpath("$.name")
		.setProperty("X-email").jsonpath("$.email")
		.setBody().header("payload")
		.bean(Utils.class, "debug")
		
		;
	}

}
