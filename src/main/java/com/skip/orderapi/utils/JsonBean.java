package com.skip.orderapi.utils;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonBean implements Serializable{
	private static final long serialVersionUID = 1l;

	public String serialize() throws Exception {
		String json=null;
		
		// create the mapper
        ObjectMapper mapper = new ObjectMapper();
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // serialize the object
        mapper.writeValue(baos, this);
        
        json = new String(baos.toByteArray());
		
		return json;
	}
	
	public JsonBean deSerialize(String objeto) throws Exception {
		 // create the mapper
        ObjectMapper mapper = new ObjectMapper();
        
        // de-serialize JSON to object
        JsonBean obj = mapper.readValue(objeto, this.getClass());
        
		return obj;
	}

	@Override
	public String toString() {
		try {
			return serialize();
		} catch (Exception e) {
			return super.toString();
		}
	}
	
	
}
