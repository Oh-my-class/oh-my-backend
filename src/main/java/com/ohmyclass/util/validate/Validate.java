package com.ohmyclass.util.validate;

import com.ohmyclass.api.exceptions.ApiRequestException;

public class Validate {

	public static <T> void notNull(T t) {
		if (t == null)
			throw new ApiRequestException();
	}

	public static <T> void notNull(T t, String message) {
		if (t == null)
			throw new ApiRequestException(message);
	}
}
