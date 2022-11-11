package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.api.util.validation.ValidationResult;

public interface ValidatorBase<T> {

	ValidationResult validate(T t);
}
