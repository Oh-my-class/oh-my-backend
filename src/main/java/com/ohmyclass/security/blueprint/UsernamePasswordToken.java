package com.ohmyclass.security.blueprint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsernamePasswordToken {

	private String username;

	private String password;
}
