package com.ohmyclass.util.validators.types;

/**
 * Determines if the validation was successful or not
 * @author Z-100
 */
public enum Outcome {
	ACCEPT, REJECT;

	public boolean isAccept() {
		return this == ACCEPT;
	}

	public boolean isReject() {
		return this == REJECT;
	}
}
