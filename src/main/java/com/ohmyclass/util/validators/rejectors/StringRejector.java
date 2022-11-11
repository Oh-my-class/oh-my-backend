package com.ohmyclass.util.validators.rejectors;

import com.ohmyclass.util.validators.Pass;
import com.ohmyclass.util.validators.collectors.ReasonCollector;

public class StringRejector extends BaseRejector<String> {

	public StringRejector(Pass pass, String entry) {
		super(pass, entry);
	}

	public ReasonCollector ifEmpty() {

		if (entry == null || entry.isEmpty()) {
			this.reject();
		}

		return this.pass;
	}
}
