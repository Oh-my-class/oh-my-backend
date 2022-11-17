package com.ohmyclass.util.validators;

import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.api.util.validation.types.ValidationStatus;
import com.ohmyclass.util.validators.collectors.FieldCollector;
import com.ohmyclass.util.validators.collectors.ReasonCollector;
import com.ohmyclass.util.validators.types.Outcome;
import com.ohmyclass.util.validators.types.ValidationField;
import com.ohmyclass.util.validators.types.ValidationReason;
import lombok.*;

/**
 * Pass, passed by the rejectors in order to determine the {@link Outcome}
 * @author Z-100
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pass implements ReasonCollector, FieldCollector {

	ValidationReason reason;

	ValidationField location;

	Outcome outcome;

	public static Pass reject() {
		return new Pass(null, null, Outcome.REJECT);
	}

	@Override
	public ReasonCollector field(ValidationField location) {
		this.location = location;
		return this;
	}

	@Override
	public FieldCollector reason(ValidationReason reason) {
		this.reason = reason;
		return this;
	}

	public void finish(ValidationResult validationResult) {

		if (this.outcome == null) {
			throw new RuntimeException("Pass outcome is null");
		}

		if (this.outcome.isReject()) {
			validationResult.add(ValidationStatus.ERROR, this.reason.getReason(), this.location.getField());
		}

	}
}
