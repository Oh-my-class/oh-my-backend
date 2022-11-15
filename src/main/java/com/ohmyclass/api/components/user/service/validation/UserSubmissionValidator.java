package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.service.validation.types.UserValidationField;
import com.ohmyclass.api.components.user.service.validation.types.UserValidationReason;
import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.api.util.validation.ValidatorBase;
import com.ohmyclass.util.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserSubmissionValidator implements ValidatorBase<UserInDTO> {

	public ValidationResult validate(UserInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getUsername()).ifEmpty()
				.reason(UserValidationReason.USERNAME_NULL)
				.field(UserValidationField.USERNAME)
				.finish(validationResult);

		Validator.reject(user.getEmail()).ifEmpty()
				.reason(UserValidationReason.EMAIL_NULL)
				.field(UserValidationField.EMAIL)
				.finish(validationResult);

		Validator.reject(user.getPassword()).ifEmpty()
				.reason(UserValidationReason.PASSWORD_NULL)
				.field(UserValidationField.PASSWORD)
				.finish(validationResult);

		Validator.reject(user.getPassword()).ifPasswordInvalid()
				.reason(UserValidationReason.PASSWORD_INVALID)
				.field(UserValidationField.PASSWORD)
				.finish(validationResult);

		return validationResult;
	}
}
