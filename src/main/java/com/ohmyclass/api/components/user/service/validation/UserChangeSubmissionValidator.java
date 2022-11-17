package com.ohmyclass.api.components.user.service.validation;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.service.validation.types.UserChangeValidationField;
import com.ohmyclass.api.components.user.service.validation.types.UserChangeValidationReason;
import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.api.util.validation.ValidatorBase;
import com.ohmyclass.util.validators.Validator;
import org.springframework.stereotype.Component;

@Component
public class UserChangeSubmissionValidator implements ValidatorBase<UserChangeInDTO> {

	public ValidationResult validate(UserChangeInDTO user) {

		ValidationResult validationResult = ValidationResult.ok();

		Validator.reject(user.getNewEmail()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_EMAIL_NULL)
				.field(UserChangeValidationField.NEW_EMAIL)
				.finish(validationResult);

		Validator.reject(user.getNewPassword()).ifEmpty()
				.reason(UserChangeValidationReason.NEW_PASSWORD_NULL)
				.field(UserChangeValidationField.NEW_PASSWORD)
				.finish(validationResult);

		Validator.reject(user.getNewPassword()).ifPasswordInvalid()
				.reason(UserChangeValidationReason.NEW_PASSWORD_INVALID)
				.field(UserChangeValidationField.NEW_PASSWORD)
				.finish(validationResult);

		return validationResult;
	}
}
