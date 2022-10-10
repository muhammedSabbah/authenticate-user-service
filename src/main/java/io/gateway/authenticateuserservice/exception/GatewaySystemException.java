package io.gateway.authenticateuserservice.exception;

import io.gateway.authenticateuserservice.utils.StatusCode;

public class GatewaySystemException extends GatewayException {

	private static final long serialVersionUID = 1L;

	public GatewaySystemException(StatusCode statusCode) {
		super(statusCode);
	}
	
	public GatewaySystemException(StatusCode statusCode, Object[] parameters) {
		super(statusCode, parameters);
	}

}
