package com.ohmyclass.util.validators;

import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.api.util.validation.http.ValidationStatus;
import com.ohmyclass.util.validators.collectors.LocationCollector;
import com.ohmyclass.util.validators.collectors.ReasonCollector;
import com.ohmyclass.util.validators.other.Outcome;
import com.ohmyclass.util.validators.other.ValidationLocation;
import com.ohmyclass.util.validators.other.ValidationReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pass implements ReasonCollector, LocationCollector {

	ValidationReason reason;

	ValidationLocation location;

	Outcome outcome;

	public static Pass reject() {
		return new Pass(null, null, Outcome.REJECT);
	}

	@Override
	public ReasonCollector location(ValidationLocation location) {
		this.location = location;
		return this;
	}

	@Override
	public LocationCollector reason(ValidationReason reason) {
		this.reason = reason;
		return this;
	}

	public Outcome finish(ValidationResult validationResult) {

		if (this.outcome == null) {
			throw new RuntimeException("Pass outcome is null");
		}

		if (this.outcome.isReject()) {
			validationResult.add(ValidationStatus.ERROR, this.reason.getReason(), this.location.getLocation());
		}

		return this.outcome;
	}
}
