package com.ohmyclass.util.validators.rejectors;

import com.ohmyclass.util.validators.other.Outcome;
import com.ohmyclass.util.validators.Pass;
import com.ohmyclass.util.validators.collectors.ReasonCollector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseRejector<T> {

	protected Pass pass;

	protected T entry;

	public ReasonCollector ifNull() {

		if (entry == null) {
			reject();
		} else {
			accept();
		}

		return this.pass;
	}

	protected void accept() {
		this.pass.setOutcome(Outcome.ACCEPT);
	}

	protected void reject() {
		this.pass.setOutcome(Outcome.REJECT);
	}
}
