package com.ohmyclass.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Handles various exceptions thrown by the API
 *
 * @author z-100
 */
@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

		HttpStatus status = HttpStatus.BAD_REQUEST;

		ApiException exception = new ApiException(
				e.getMessage(),
				e.getCause(),
				status,
				ZonedDateTime.now(ZoneId.of("Z"))
		);

		return new ResponseEntity<>(exception, status);
	}
}
