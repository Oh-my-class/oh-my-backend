package com.ohmyclass.security.blueprint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsernamePasswordToken {

	private String username;

	private String password;
}
