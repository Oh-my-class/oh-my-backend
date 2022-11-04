package com.ohmyclass.api.exceptions;

import com.ohmyclass.api.util.validation.ValidationResultEntry;
import com.ohmyclass.api.util.validation.http.ValidationStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

public record ApiException(String message, Throwable cause, HttpStatus status, ZonedDateTime timestamp) {

	public ValidationResultEntry toValidationResultEntry() {
		return new ValidationResultEntry(ValidationStatus.resolveTypeByHttpStatusId(status.value()), message, null/*, List.of(cause.getCause().toString())*/);
	}
}
