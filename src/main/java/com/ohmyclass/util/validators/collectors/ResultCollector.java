package com.ohmyclass.util.validators.collectors;

import com.ohmyclass.api.util.validation.ValidationResult;

/**
 * Adds errors/warnings/infos to the passed {@link ValidationResult} on reject
 * @author Z-100
 */
public interface ResultCollector {

	void finish(ValidationResult validationResult);
}
