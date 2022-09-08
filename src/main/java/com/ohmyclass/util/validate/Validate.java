package com.ohmyclass.util.validate;

import java.util.Optional;

public class Validate {

	public static <T> void notNull(T t) {
		if (t == null)
			throw new IllegalArgumentException("Object was null");
	}

	public static <T extends Optional<?>> T notNull(T t) {
		if (t == null || t.isEmpty())
			throw new IllegalArgumentException("Object was null");

		return t;
	}
}
