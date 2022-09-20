package com.ohmyclass.security.filter;

import com.ohmyclass.security.blueprint.UsernamePasswordToken;

import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

public abstract class CustomHttpFilter extends HttpFilter {

	public CustomHttpFilter() {
		super();
	}

	protected final Function<HttpServletRequest, UsernamePasswordToken> filterBasicAuthFrom = (request) -> {
		final String authorization = request.getHeader("Authorization");
		UsernamePasswordToken token = null;

		if (authorization != null && authorization.toLowerCase().startsWith("basic")) {

			String base64Credentials = authorization.substring("Basic".length()).trim();

			byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
			String credentials = new String(credDecoded, StandardCharsets.UTF_8);

			// credentials = username:password
			final String[] values = credentials.split(":", 2);

			token = new UsernamePasswordToken(values[0], values[1]);
		}

		return token;
	};
}
