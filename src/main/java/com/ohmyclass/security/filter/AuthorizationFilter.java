package com.ohmyclass.security.filter;

import com.ohmyclass.security.blueprint.UsernamePasswordToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.BiFunction;

@Component
@AllArgsConstructor
public class AuthorizationFilter extends CustomHttpFilter {

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		UsernamePasswordToken token = filterBasicAuthFrom.apply(request);

		if (notAuthorized.apply(token, request)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}

		chain.doFilter(request, response); // (4)
	}

	private final BiFunction<UsernamePasswordToken, HttpServletRequest, Boolean> notAuthorized = (token, request) -> {
		// Implement role check system
		return false;
	};
}
