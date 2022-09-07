package com.ohmyclass.api.util;

import com.ohmyclass.api.util.validation.ValidationResult;

public abstract class Response<T> {

	private T t;

	private ValidationResult validationResult = ValidationResult.ok();
}
