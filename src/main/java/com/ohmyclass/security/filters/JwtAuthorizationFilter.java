package com.ohmyclass.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ohmyclass.api.exceptions.ApiRequestException;
import com.ohmyclass.security.util.JwtTokenUtil;
import com.ohmyclass.server.properties.JwtConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * This filter is used to validate the JWT token sent by the client.
 *
 * @author z-100
 */
@Log4j2
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final JwtTokenUtil tokenUtil;

	private JwtConstants constants;

	private final Predicate<HttpServletRequest> isUnprotectedUrl = (req) ->
			constants.getUriwhitelist().stream().anyMatch(req.getRequestURI()::equals);


	public JwtAuthorizationFilter(JwtTokenUtil tokenUtil, JwtConstants constants) {
		this.tokenUtil = tokenUtil;
		this.constants = constants;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Guard
		if (isUnprotectedUrl.test(request)) {

			log.debug("Security skipped: {}", request.getServletPath());
			filterChain.doFilter(request, response);
			return;
		}

		String authorizationHeader = request.getHeader(AUTHORIZATION);

		// Guard
		if (!tokenUtil.isValidBearer(authorizationHeader)) {

			throw new ApiRequestException("Invalid bearer token");
		}

		createSessionFrom(authorizationHeader);

		filterChain.doFilter(request, response);
	}

	private void createSessionFrom(String authorizationHeader) {

		DecodedJWT decodedJWT;

		try {
			decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);
		} catch (Exception e) {
			throw new ApiRequestException("Invalid token");
		}

		String username = decodedJWT.getSubject();
		String[] roles = decodedJWT.getClaim(constants.getClaims().get("roles")).asArray(String.class);

		List<SimpleGrantedAuthority> authorities = roles != null ? Stream.of(roles)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()) : new ArrayList<>();

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}