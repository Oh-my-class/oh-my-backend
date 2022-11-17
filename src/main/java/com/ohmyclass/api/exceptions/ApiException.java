package com.ohmyclass.api.exceptions;

import lombok.Getter;

/**
 * Exception thrown when an API request fails.
 *
 * @author z-100
 */
public class ApiException extends RuntimeException {

	@Getter
	private String message;

	public ApiException() {
		super();
	}

	public ApiException(String message) {
		this.message = message;
	}

	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
