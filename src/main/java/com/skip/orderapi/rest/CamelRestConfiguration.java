package com.skip.orderapi.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skip.orderapi.repository.CustomerRepository;

@Component
public class CamelRestConfiguration extends RouteBuilder{
	
	@Autowired private CustomerRepository customerRepository;

	@Override
	public void configure() throws Exception {
		restConfiguration().contextPath("/api/v1").component("restlet").dataFormatProperty("prettyPrint", "true")
		.host("0.0.0.0").port(8181).apiContextPath("/api-docs").bindingMode(RestBindingMode.auto)
		.apiProperty("init.base.path", "api/rest")
		.apiProperty("init.api.path", "/api-docs")
		.apiProperty("init.api.description", "Skip API")
        .apiProperty("api.title", "Skip")
        .apiProperty("api.version", "1.0.0")
        .apiProperty("cors", "true");
		
	}

}
