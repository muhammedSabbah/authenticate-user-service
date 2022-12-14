package io.gateway.authenticateuserservice.exception;

import io.gateway.authenticateuserservice.utils.StatusCode;

public class GatewayBusinessException extends GatewayException {

	private static final long serialVersionUID = 1L;

	public GatewayBusinessException(StatusCode statusCode) {
		super(statusCode);
	}
	
	public GatewayBusinessException(StatusCode statusCode, Object[] parameters) {
		super(statusCode, parameters);
	}

}
