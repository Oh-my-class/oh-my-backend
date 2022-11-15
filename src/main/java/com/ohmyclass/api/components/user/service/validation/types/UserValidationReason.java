package com.ohmyclass.api.components.user.service.validation.types;

import com.ohmyclass.util.validators.other.ValidationReason;

public enum UserValidationReason implements ValidationReason {

	USERNAME_NULL("Username cannot be null"),
	PASSWORD_NULL("Password cannot be null"),
	PASSWORD_INVALID("Password doesn't match regex");

	String reason;

	UserValidationReason(String reason) {}

	@Override
	public String getReason() {
		return reason;
	}
}
