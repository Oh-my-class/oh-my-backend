package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.api.util.validation.ValidationResult;

public interface ResultCollector {

	void finish(ValidationResult validationResult);
}
