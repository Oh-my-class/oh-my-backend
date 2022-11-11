package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.util.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserSubmissionValidator implements ValidatorBase<UserInDTO> {

	public ValidationResult validate(UserInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getUsername()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.location(UserValidationLocation.USERNAME)
				.finish(validationResult);

		Validator.reject(user.getEmail()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.location(UserValidationLocation.USERNAME)
				.finish(validationResult);

		Validator.reject(user.getPassword()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.location(UserValidationLocation.USERNAME)
				.finish(validationResult);

		return validationResult;
	}
}
