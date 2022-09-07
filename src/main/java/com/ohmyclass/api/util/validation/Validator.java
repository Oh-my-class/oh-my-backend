package com.ohmyclass.api.util.validation;

public class Validator {

	public static <T> T notNull(T t) {
		if (t == null)
			throw new IllegalArgumentException("Object was null");

		return t;
	}
}
