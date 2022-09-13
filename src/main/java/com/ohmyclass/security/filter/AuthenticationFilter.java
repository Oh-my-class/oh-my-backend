package com.ohmyclass.security.filter;

import com.ohmyclass.security.DatabaseUserDetailsService;
import com.ohmyclass.security.blueprint.UsernamePasswordToken;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class AuthenticationFilter extends CustomHttpFilter {

	private DatabaseUserDetailsService dbUserDetailsService;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

//		if (false /*|| !request.getRequestURI().equals("/user/register")*/) {
			System.out.println("Authentication filter triggered");

			UsernamePasswordToken token = filterBasicAuthFrom.apply(request);

			System.out.println("token :   " + token.getUsername());
			System.out.println("token :   " + token.getPassword());
			System.out.println(request.getRequestURI());

			if (notAuthenticated(token)) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			}
//		}
		chain.doFilter(request, response);
	}

	/**
	 * Either no- or wrong username/password
	 */
	private boolean notAuthenticated(UsernamePasswordToken token) {

		UserDetails userDetails = dbUserDetailsService.loadUserByUsername(token.getUsername());

		if (userDetails == null) {
			System.out.println("USER DETAILS IS NULL");
			return true;
		}

		return !userDetails.getUsername().equals(token.getUsername())
				&& !userDetails.getPassword().equals(token.getPassword());
	}
}
