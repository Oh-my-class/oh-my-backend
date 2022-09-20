package com.ohmyclass.security.filter;

import com.ohmyclass.security.DatabaseUserDetailsService;
import com.ohmyclass.security.blueprint.ProtectedUrls;
import com.ohmyclass.security.blueprint.UsernamePasswordToken;
import com.ohmyclass.util.log.Logger;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends CustomHttpFilter {

	private DatabaseUserDetailsService dbUserDetailsService;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//TODO On login, write user details from BA -> body
		if (uriIsProtected(request)) {
			UsernamePasswordToken token = filterBasicAuthFrom.apply(request);

			if (notAuthenticated(token)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private boolean uriIsProtected(HttpServletRequest request) {
		return Arrays.stream(ProtectedUrls.values())
				.noneMatch(uri -> uri.value().equals(request.getRequestURI()));
	}

	/**
	 * Either no- or wrong username/password
	 */
	private boolean notAuthenticated(UsernamePasswordToken token) {

		UserDetails userDetails = dbUserDetailsService.loadUserByUsername(token.getUsername());

		if (userDetails == null) {
			Logger.debug(this.getClass().getSimpleName(), "User not found");
			return true;
		}

		return !userDetails.getUsername().equals(token.getUsername())
				&& !userDetails.getPassword().equals(token.getPassword());
	}
}
