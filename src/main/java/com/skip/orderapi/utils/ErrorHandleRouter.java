package com.skip.orderapi.utils;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import com.skip.orderapi.dto.ExceptionDTO;

@Component
public class ErrorHandleRouter {
	public ExceptionDTO error(Exchange exchange) {
		Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
		ExceptionDTO error = new ExceptionDTO();
		error.setMessage(cause.getMessage());
		
		return error;
	}
}
