package com.skip.orderapi.dto;

import com.skip.orderapi.utils.JsonBean;

public class ExceptionDTO extends JsonBean{
	private String message;
	private String detailMessage;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetailMessage() {
		return detailMessage;
	}
	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	
	
}
