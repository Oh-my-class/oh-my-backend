//package com.ohmyclass.security.filter;
//
//import com.ohmyclass.security.blueprint.JwtTokenUtil;
//import com.ohmyclass.security.blueprint.ProtectedUrls;
//import io.jsonwebtoken.ExpiredJwtException;
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//
//@Component
//@AllArgsConstructor
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//	private final JwtTokenUtil jwtTokenUtil;
//
//	private final JwtUserDetailsService jwtUserDetailsService;
//
//	// Token exist
//	// No: SecurityAuthProvider -> extract userdetails + validate
//	// Yes: Get userdetails by uname -> JwtTokenUtil.validate (token, details)
//		//
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws ServletException, IOException {
//
//		System.out.println("==========JwtRequestFilter triggered");
//
//		if (Arrays.stream(ProtectedUrls.values()).anyMatch(uri -> uri.value().equals(request.getRequestURI()))) {
//			chain.doFilter(request, response);
//			return;
//		}
//
//		final String requestHeader = request.getHeader("Authorization");
//
//		String username = null;
//		String jwtToken = null;
//
//
//		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
//
//			jwtToken = requestHeader.substring(7);
//
//			try {
//
//				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
//
//			} catch (IllegalArgumentException e) {
//
//				throw new IllegalArgumentException("Unable to get JWT Token");
//
//			} catch (ExpiredJwtException e) {
//
//				throw new IllegalArgumentException("JWT Token has expired");
//			}
//		} else {
//
//			logger.warn("JWT does not begin with Bearer String or is not set");
//
//			throw new IllegalArgumentException("JWT does not begin with Bearer String or is not set");
//		}
//
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
//
//			// Token valid: Manually set auth
//			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
//
//				logger.debug("Token is valid");
//
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//
//				usernamePasswordAuthenticationToken
//						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
//		}
//
//		chain.doFilter(request, response);
//	}
//}