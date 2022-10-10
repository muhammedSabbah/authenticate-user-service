package io.gateway.authenticateuserservice.utils;

public enum Severity {

	SUCCESS("SUCCESS"),
	ERROR("ERROR"), 
	WARNING("WARNING"),
	FATAL("FATAL");

	private String value;

	Severity(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static Severity fromValue(String value) {
		for (Severity s : Severity.values()) {
			if (s.value().equals(value)) {
				return s;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
