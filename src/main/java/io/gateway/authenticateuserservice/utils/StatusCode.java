package io.gateway.authenticateuserservice.utils;

public enum StatusCode {

	SUCCESS(200, "SUCCESS", Severity.SUCCESS),
	UNAUTHORIZED(401, "Unauthorized", Severity.ERROR),
	USER_NOT_EXIST(1001, "User is not Exist", Severity.FATAL),
	USERS_EMPTY(1002, "Empty data.", Severity.FATAL),
	ROLE_ALREADY_ASSIGNED(1003, "User has already {} role", Severity.ERROR);

	private int code;
	private String description;
	private Severity severity;

	private StatusCode(int code, String description, Severity severity) {
		this.code = code;
		this.description = description;
		this.severity = severity;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Severity getSeverity() {
		return severity;
	}

}
