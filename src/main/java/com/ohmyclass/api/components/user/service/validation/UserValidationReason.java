package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.util.validators.other.ValidationReason;

public enum UserValidationReason implements ValidationReason {
	USERNAME_NULL("Username cannot be null");

	String reason;

	UserValidationReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String getReason() {
		return reason;
	}
}
