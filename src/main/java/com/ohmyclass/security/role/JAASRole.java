package com.ohmyclass.security.role;

import lombok.AllArgsConstructor;

import javax.security.auth.Subject;
import java.io.Serializable;
import java.security.Principal;

@AllArgsConstructor
public class JAASRole implements Principal, Serializable {

	private String name;

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean implies(Subject subject) {
		return Principal.super.implies(subject);
	}
}
