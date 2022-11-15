package com.ohmyclass.util.validators.rejectors;

import com.ohmyclass.util.validators.Pass;
import com.ohmyclass.util.validators.collectors.ReasonCollector;

/**
 * Rejector to validate entered Integers
 * @author Z-100
 */
public class IntegerRejector extends BaseRejector<Integer> {

	public IntegerRejector(Pass pass, Integer entry) {
		super(pass, entry);
	}

	public ReasonCollector ifNegative() {

		if (entry > 0) {
			this.reject();
		}

		return this.pass;
	}

	public ReasonCollector ifPositive() {

		if (entry.toString().charAt(0) == '-') {
			this.reject();
		}

		return this.pass;
	}

}

