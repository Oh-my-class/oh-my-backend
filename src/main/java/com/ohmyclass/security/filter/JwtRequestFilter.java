package com.ohmyclass.security.filter;

import com.ohmyclass.security.blueprint.JwtTokenUtil;
import com.ohmyclass.security.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

	private final JwtTokenUtil jwtTokenUtil;

	private final JwtUserDetailsService jwtUserDetailsService;

	// Token exist
	// No: SecurityAuthProvider -> extract userdetails + validate
	// Yes: Get userdetails by uname -> JwtTokenUtil.validate (token, details)
		//

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;


		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {

			jwtToken = requestHeader.substring(7);

			try {

				username = jwtTokenUtil.getUsernameFromToken(jwtToken);

			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, response);
	}

}