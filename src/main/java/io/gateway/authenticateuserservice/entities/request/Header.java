package io.gateway.authenticateuserservice.entities.request;

import io.gateway.authenticateuserservice.utils.StatusCode;

public class Header {

	private Long code;

	private String message;

	public Header() {
	}

	public Header(Long code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Header(StatusCode statusCode) {
		this.code = (long) statusCode.getCode();
		this.message = statusCode.getDescription();
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
