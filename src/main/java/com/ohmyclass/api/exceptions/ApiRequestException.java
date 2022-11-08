package com.ohmyclass.api.exceptions;

import lombok.Getter;

/**
 * Exception thrown when an API request fails.
 *
 * @author z-100
 */
public class ApiRequestException extends RuntimeException {

	@Getter
	private String message;

	public ApiRequestException() {
		super();
	}

	public ApiRequestException(String message) {
		this.message = message;
	}

	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
