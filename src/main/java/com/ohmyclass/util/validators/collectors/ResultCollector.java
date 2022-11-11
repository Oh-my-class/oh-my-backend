package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.util.validators.other.Outcome;

public interface ResultCollector {

	Outcome finish(ValidationResult validationResult);
}
