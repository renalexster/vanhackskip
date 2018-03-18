package com.skip.orderapi.utils;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	public Object debug(Exception e, @Body Object body) {
		LOG.info(String.format("DEBUG body payload %s", body.toString()));
		return body;
	}
}
