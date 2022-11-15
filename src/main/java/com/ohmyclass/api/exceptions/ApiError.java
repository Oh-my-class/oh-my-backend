package com.ohmyclass.api.exceptions;

import com.ohmyclass.api.util.validation.ValidationResultEntry;
import com.ohmyclass.api.util.validation.types.ValidationStatus;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record ApiError(String message, HttpStatus status, Throwable cause, ZonedDateTime timestamp) {

	public ValidationResultEntry toValidationResultEntry() {
		return new ValidationResultEntry(ValidationStatus.resolveTypeByHttpStatusId(status.value()), message);
	}
}
