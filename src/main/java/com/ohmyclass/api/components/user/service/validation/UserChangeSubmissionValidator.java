package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.util.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserChangeSubmissionValidator implements ValidatorBase<UserChangeInDTO> {

	public ValidationResult validate(UserChangeInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getNewEmail()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.location(UserValidationLocation.USERNAME)
				.finish(validationResult);

		Validator.reject(user.getNewPassword()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.location(UserValidationLocation.USERNAME)
				.finish(validationResult);

		return validationResult;
	}
}
