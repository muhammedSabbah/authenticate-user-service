package io.gateway.authenticateuserservice.utils;

public enum USER_ROLE {

	ROLE_USER("ROLE_USER"),
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN");
	
	private String value;
	
	USER_ROLE(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
