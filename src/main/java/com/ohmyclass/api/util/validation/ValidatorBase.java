package com.ohmyclass.api.util.validation;

/**
 * Base of a {@link com.ohmyclass.api.util.SubmissionProcessor}-validator
 * @param <T> Type of to be passed object
 */
public interface ValidatorBase<T> {

	ValidationResult validate(T t);
}
