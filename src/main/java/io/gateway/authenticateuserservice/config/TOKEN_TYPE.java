package io.gateway.authenticateuserservice.config;

public enum TOKEN_TYPE {

	ACCESS_TOKEN("ACCESS_TOKEN", 60 * 60 * 1000L),
	REFRESH_TOKEN("REFRESH_TOKEN", 24 * 60 * 60 * 1000L);
	
	private String value;
	private Long expiration;
	
	TOKEN_TYPE(String value, Long expiration) {
		this.value = value;
		this.expiration = expiration;
	}
	
	public String value() {
		return this.value;
	}
	
	public Long expiration() {
		return this.expiration;
	}
}
