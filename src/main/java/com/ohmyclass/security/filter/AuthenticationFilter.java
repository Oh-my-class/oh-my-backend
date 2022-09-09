package com.ohmyclass.security.filter;

import com.ohmyclass.security.DatabaseUserDetailsService;
import com.ohmyclass.security.blueprint.UsernamePasswordToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends CustomHttpFilter {

	private final DatabaseUserDetailsService dbUserDetailsService;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		UsernamePasswordToken token = filterBasicAuthFrom.apply(request);

		if (notAuthenticated.test(token)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * Either no- or wrong username/password
	 */
	private final Predicate<UsernamePasswordToken> notAuthenticated = (token) -> {

		UserDetails userDetails = dbUserDetailsService.loadUserByUsername(token.getUsername());

		if (userDetails == null)
			return true;

		return !userDetails.getUsername().equals(token.getUsername())
				&& !userDetails.getPassword().equals(token.getPassword());
	};
}