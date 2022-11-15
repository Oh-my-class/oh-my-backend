package com.ohmyclass.util.validators.other.types;

public enum Outcome {
	ACCEPT, REJECT;

	public boolean isAccept() {
		return this == ACCEPT;
	}

	public boolean isReject() {
		return this == REJECT;
	}
}
