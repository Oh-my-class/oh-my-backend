package com.ohmyclass.api.util.communication;

import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.api.util.validation.types.ValidationStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateResponseService {

	public static <T extends Response> void newError(T t, String message, ValidationResult validationResult) {
		validationResult.add(ValidationStatus.ERROR, message);
		t.setValidationResult(validationResult);
	}

	public static <T extends Response> void newSuccess(T t, String message, ValidationResult validationResult) {
		validationResult.add(ValidationStatus.OK, message);
		t.setValidationResult(validationResult);
	}

	public static <T extends HttpServletResponse> void newError(T t, int status, String message) throws IOException {
		t.setStatus(status);
		t.getWriter().write(message);
	}
}
