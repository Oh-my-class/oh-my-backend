package com.ohmyclass.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohmyclass.security.service.JwtUserDetailsService;
import com.ohmyclass.security.blueprint.ProtectedUrls;
import com.ohmyclass.security.blueprint.UsernamePasswordToken;
import com.ohmyclass.util.log.Logger;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Component
@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {


	private JwtUserDetailsService dbJwtUserDetailsService;

	private AuthenticationManager authManager;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

		/*//TODO On login, write user details from BA -> body
		if (uriIsNotProtected(request)) {
			UsernamePasswordToken token = filterBasicAuthFrom.apply(request);

			if (notAuthenticated(token))
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		}*/

		try {
			User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);

			return authManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean uriIsNotProtected(HttpServletRequest request) {
		return Arrays.stream(ProtectedUrls.values())
				.noneMatch(uri -> uri.value().equals(request.getRequestURI()));
	}

	/**
	 * Either no- or wrong username/password
	 */
	private boolean notAuthenticated(UsernamePasswordToken token) {

		UserDetails userDetails = dbJwtUserDetailsService.loadUserByUsername(token.getUsername());

		if (userDetails == null) {
			Logger.debug(this.getClass().getSimpleName(), "User not found");
			return true;
		}

		return !userDetails.getUsername().equals(token.getUsername())
				&& !userDetails.getPassword().equals(token.getPassword());
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			FilterChain chain, Authentication auth) throws IOException {

//		String token = JWT.create()
//				.withSubject(((User) auth.getPrincipal()).getUsername())
//				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//				.sign(Algorithm.HMAC512(SECRET.getBytes()));

		String body = ((User) auth.getPrincipal()).getUsername() + " " + "token";

		res.getWriter().write(body);
		res.getWriter().flush();
	}
}
