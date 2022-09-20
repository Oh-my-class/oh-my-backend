package com.ohmyclass.security.blueprint;

/**
 * TODO Replace by cfg
 * Used to add to-be-ignored URIs
 */
public enum ProtectedUrls {
	LOGIN("/user/login"),
	REGISTER("/user/register");

	String uri;

	ProtectedUrls(String uri) {
		this.uri = uri;
	}

	public String value() {
		return uri;
	}
}
