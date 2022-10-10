package io.gateway.authenticateuserservice.exception;

import java.text.MessageFormat;

import io.gateway.authenticateuserservice.utils.Severity;
import io.gateway.authenticateuserservice.utils.StatusCode;

public class GatewayException extends Exception {
	private static final long serialVersionUID = 1L;
	private static final int GENERAL_ERROR_CODE = 99999;

	private int errorCode;
	private Severity severity;
	private Object[] parameters;
	
	public GatewayException(StatusCode statusCode) {
		this(statusCode, null, null);
	}

	public GatewayException(StatusCode statusCode, Object[] parameters) {
		this(statusCode, null, parameters);
	}
	public GatewayException(StatusCode statusCode, Throwable reason, Object[] parameters) {
		super(buildMessage(statusCode, reason, parameters));
		if (statusCode != null) {
			this.setErrorCode(statusCode.getCode());
			this.setSeverity(statusCode.getSeverity());
		} else {
			this.setErrorCode(GENERAL_ERROR_CODE);
			this.setSeverity(Severity.FATAL);
		}
		this.parameters = parameters;
	}

	public static String buildMessage(StatusCode statusCode, Throwable reason, Object[] parameters) {
		return buildMessage(statusCode.getSeverity(), statusCode.getCode(), statusCode.getDescription(), reason,
				parameters);
	}

	public static String buildMessage(Severity severity, int code, String description, Throwable reason,
			Object[] parameters) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(severity);
		buffer.append(":");
		buffer.append(code);
		buffer.append(":");
		if (description != null && parameters != null && parameters.length > 0) {
			final MessageFormat format = new MessageFormat(description);
			buffer.append(format.format(parameters));
		} else {
			buffer.append(description);
		}
		if (reason != null) {
			buffer.append(":");
			buffer.append(reason.getMessage());
		}
		return buffer.toString();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Severity getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

}
