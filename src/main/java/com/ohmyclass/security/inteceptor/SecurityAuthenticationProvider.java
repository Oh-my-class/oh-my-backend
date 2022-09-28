package com.ohmyclass.security.inteceptor;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

	public SecurityAuthenticationProvider() {
		super();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		//Validate userdetails from request with userdetails from database {@Link JwtUserDetailsService}

		final String name = authentication.getName();
		final String password = authentication.getCredentials().toString();

		if (name.equals("admin") && password.equals("system")) {

			final List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

			final UserDetails principal = new User(name, password, grantedAuths);

			return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
		}

		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
