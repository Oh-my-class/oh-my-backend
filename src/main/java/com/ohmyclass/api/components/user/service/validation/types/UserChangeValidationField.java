package com.ohmyclass.api.components.user.service.validation.types;

import com.ohmyclass.util.validators.types.ValidationField;

public enum UserChangeValidationField implements ValidationField {

	NEW_EMAIL("newEmail"),
	NEW_PASSWORD("newPassword");
	private final String location;

	UserChangeValidationField(String location) {
		this.location = location;
	}

	@Override
	public String getField() {
		return location;
	}
}
