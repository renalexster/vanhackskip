package com.skip.orderapi.utils;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	public Object debug(Exchange exchange, @Body Object body) {
		LOG.info(String.format("DEBUG body payload %s", body != null? body.toString():null));exchange.getIn().getHeader("payload");
		return body;
	}
}
