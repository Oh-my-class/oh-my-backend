package com.ohmyclass.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ohmyclass.security.util.JwtTokenUtil;
import com.ohmyclass.server.properties.JwtConstants;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final JwtTokenUtil tokenUtil;

	private JwtConstants constants;

	private final Predicate<String> isUnprotectedUrl =
			(req) -> constants.getUriwhitelist().stream().anyMatch(req::contains);


	public JwtAuthorizationFilter(JwtTokenUtil tokenUtil, JwtConstants constants) {

		this.tokenUtil = tokenUtil;
		this.constants = constants;
	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {

		return isUnprotectedUrl.test(request.getRequestURI());
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		createSessionFrom(request.getHeader(AUTHORIZATION));

		filterChain.doFilter(request, response);
	}

	private void createSessionFrom(String authorizationHeader) {

		tokenUtil.validateBearer(authorizationHeader);

		DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);

		tokenUtil.validateTokenExpiration(decodedJWT);

		String username = decodedJWT.getSubject();
		String[] roles = decodedJWT.getClaim(constants.getClaims().get("roles")).asArray(String.class);

		List<SimpleGrantedAuthority> authorities = roles != null
				? Stream.of(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList())
				: new ArrayList<>();

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
