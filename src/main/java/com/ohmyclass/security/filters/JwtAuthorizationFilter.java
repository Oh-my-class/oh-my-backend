package com.ohmyclass.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ohmyclass.security.util.JwtTokenUtil;
import com.ohmyclass.server.properties.JwtConstants;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private JwtTokenUtil tokenUtil;

	private JwtConstants constants;

	private final Predicate<HttpServletRequest> isProtectedUrl = (req) -> constants.getUnprotectedurls().stream()
			.anyMatch(unprotectedUri -> unprotectedUri.equals(req.getServletPath()));;


	public JwtAuthorizationFilter(JwtTokenUtil tokenUtil, JwtConstants constants) {
		this.tokenUtil = tokenUtil;
		this.constants = constants;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Guard
		if (isProtectedUrl.test(request)) {

			log.debug("Security skipped: {}", request.getServletPath());
			filterChain.doFilter(request, response);
		}

		String authorizationHeader = request.getHeader(AUTHORIZATION);

		// Guard
		if (!tokenUtil.isValid(authorizationHeader)) {

			log.debug("Invalid bearer token: {}", authorizationHeader);
			filterChain.doFilter(request, response);
		}

		createSessionFrom(authorizationHeader);

		filterChain.doFilter(request, response);
	}

	private void createSessionFrom(String authorizationHeader) {

		DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);

		String username = decodedJWT.getSubject();
		String[] roles = decodedJWT.getClaim(constants.getClaims().get("roles")).asArray(String.class);

		List<SimpleGrantedAuthority> authorities = Stream.of(roles)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}
}
