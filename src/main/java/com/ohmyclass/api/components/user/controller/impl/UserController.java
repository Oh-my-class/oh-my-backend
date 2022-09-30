package com.ohmyclass.api.components.user.controller.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ohmyclass.api.components.role.entity.Role;
import com.ohmyclass.api.components.user.controller.IUserController;
import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;

import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.service.crud.impl.UserService;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.security.services.JwtUserDetailsService;
import com.ohmyclass.security.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@AllArgsConstructor
public class UserController implements IUserController {

	private final UserService userService;

	@Override
	public Response<String> register(UserInDTO registration) {
		return userService.register(registration);
	}

	@Override
	public Response<UserOutDTO> getUser(UserInDTO user) {
		return userService.getUser(user);
	}

	@Override
	public Response<UserOutDTO> updateUser(UserChangeInDTO userChangeIn) {
		return userService.update(userChangeIn);
	}

	@Override
	public Response<Boolean> deleteUser(UserInDTO user) {
		return userService.delete(user);
	}

	@Override
	public Response<UserOutDTO> passwordForgotten(UserInDTO user) {
		return userService.passwordForgotten(user);
	}

	private final JwtTokenUtil tokenUtil;

	private final JwtUserDetailsService userDetailsService;

	@GetMapping("/api/refresh-token")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String authorizationHeader = request.getHeader(AUTHORIZATION);

		if (tokenUtil.isValid(authorizationHeader)) {
			try {
				DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);
				String username = decodedJWT.getSubject();

				User user = userService.getUser(username);

				String subject = user.getUsername();
				String issuer = request.getRequestURI();
				List<String> roles = user.getRoles().stream()
						.map(Role::getName)
						.collect(Collectors.toList());

				String newAccessToken = tokenUtil.generateNewAccessToken(subject, issuer, roles);

				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(username, null, null);

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			} catch (Exception e) {
				response.setHeader("error", e.getMessage());
				response.sendError(FORBIDDEN.value());
			}
		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
}
