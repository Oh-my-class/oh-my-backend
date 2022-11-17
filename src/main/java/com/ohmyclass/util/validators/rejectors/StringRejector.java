package com.ohmyclass.util.validators.rejectors;

import com.ohmyclass.util.validators.Pass;
import com.ohmyclass.util.validators.collectors.ReasonCollector;

import java.util.regex.Pattern;

/**
 * Rejector to validate entered Strings
 * @author Z-100
 */
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

	public ReasonCollector ifPasswordInvalid() {

		Pattern passwordPattern = Pattern.compile("^.*(?=.{6,})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!&$%&? \"]).*$\n");

		if (!passwordPattern.matcher(entry).matches()) {
			this.reject();
		}

		return this.pass;
	}
}
