package com.ohmyclass.api.components.user.service.validation.types;

import com.ohmyclass.util.validators.types.ValidationField;

public enum UserValidationField implements ValidationField {

	EMAIL("email"),
	USERNAME("username"),
	PASSWORD("password"),
	NAME("name"),
	ROLE("role");

	private final String location;

	UserValidationField(String location) {
		this.location = location;
	}

	@Override
	public String getField() {
		return location;
	}
}
