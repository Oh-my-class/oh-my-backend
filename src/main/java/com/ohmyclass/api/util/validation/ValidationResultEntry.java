package com.ohmyclass.api.util.validation;

import com.ohmyclass.api.util.validation.http.ValidationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidationResultEntry {

	private ValidationStatus status;

	private String reason;

	private List<String> details;

	public ValidationResultEntry(ValidationStatus status, String reason) {
		this.status = status;
		this.reason = reason;
	}

	public ValidationResultEntry(ValidationStatus status, String reason, List<String> details) {
		this.status = status;
		this.reason = reason;
		this.details = details;
	}

	private boolean isInfo() {
		return this.status == ValidationStatus.INFO;
	}

	private boolean isWarning() {
		return this.status == ValidationStatus.WARNING;
	}

	private boolean isError() {
		return this.status == ValidationStatus.ERROR;
	}

	private boolean hasDetails() {
		return this.details.size() > 0;
	}
}

