package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.util.validators.other.ValidationLocation;

public enum UserValidationLocation implements ValidationLocation {

	EMAIL("email"),
	USERNAME("username"),
	PASSWORD("password"),
	NAME("name"),
	ROLE("role");

	private final String location;

	UserValidationLocation(String location) {
		this.location = location;
	}

	@Override
	public String getLocation() {
		return location;
	}
}
