package com.skip.orderapi.dto;

import com.skip.orderapi.utils.JsonBean;

public class JwtDTO extends JsonBean{
	private String type;
	private String key;
	
	public JwtDTO(String type, String key) {
		super();
		this.type = type;
		this.key = key;
	}

	public JwtDTO() {
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
