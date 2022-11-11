package com.ohmyclass.util.validators;

import com.ohmyclass.api.exceptions.ApiException;

import java.util.Arrays;
import java.util.Objects;

public class ObjectValidator {

	@SafeVarargs
	public static <T> void notNull(T... t) {
		if (Arrays.stream(t).anyMatch(Objects::isNull))
			throw new ApiException();
	}

	@SafeVarargs
	public static <T> void notNull(String message, T... t) {
		if (Arrays.stream(t).anyMatch(Objects::isNull))
			throw new ApiException(message);
	}
}
