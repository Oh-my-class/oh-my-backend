package com.ohmyclass.util.validators.rejectors;

import com.ohmyclass.util.validators.Pass;
import com.ohmyclass.util.validators.collectors.ReasonCollector;

/**
 * Rejector to validate entered Booleans
 * @author Z-100
 */
public class BooleanRejector extends BaseRejector<Boolean> {

	public BooleanRejector(Pass pass, Boolean entry) {
		super(pass, entry);
	}

	public ReasonCollector ifTrue() {

		if (Boolean.TRUE.equals(entry)) {
			this.reject();
		}

		return this.pass;
	}

	public ReasonCollector ifFalse() {

		if (Boolean.FALSE.equals(entry)) {
			this.reject();
		}

		return this.pass;
	}
}
