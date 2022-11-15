package com.ohmyclass.api.components.user.service.crud.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohmyclass.api.components.role.entity.Role;
import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.UserRepository;
import com.ohmyclass.api.components.user.service.crud.IUserService;
import com.ohmyclass.api.components.user.service.mapper.UserMapper;
import com.ohmyclass.api.components.user.service.processors.UserChangeSubmissionProcessor;
import com.ohmyclass.api.components.user.service.processors.UserSubmissionProcessor;
import com.ohmyclass.api.exceptions.ApiException;
import com.ohmyclass.security.util.JwtTokenUtil;
import com.ohmyclass.util.validators.*;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Log4j2
@Component
@AllArgsConstructor
public class UserService implements IUserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final JwtTokenUtil tokenUtil;

	private final UserSubmissionProcessor userSubmissionProcessor;

	private final UserChangeSubmissionProcessor userChangeSubmissionProcessor;

	@Override
	public Map<String, String> register(UserInDTO inDTO) {

		userSubmissionProcessor.process(inDTO);

		User persistedUser = userSubmissionProcessor.getPersistedEntity();

		List<String> roles = persistedUser.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.toList());

		return tokenUtil.generateNewTokenMap(persistedUser.getUsername(), "Registration", roles);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(AUTHORIZATION);

		// Guard
		if (!tokenUtil.isValidBearer(authorizationHeader)) {

			throw new ApiException("Invalid bearer token.");
		}

		try {
			DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);

			if (tokenUtil.isTokenExpired(decodedJWT)) {

				throw new ApiException("Token expired. Please login again");
			}

			String newAccessToken = createNewAccessToken(request, decodedJWT);

			new ObjectMapper().writeValue(response.getOutputStream(), newAccessToken);

		} catch (Exception e) {
			throw new ApiException("Refreshing token failed");
		}
	}

	@Override
	public UserOutDTO getUser(String username) {

		ObjectValidator.notNull("Supplied username was null", username);

		return userMapper.entityToOutDTO(userRepository.findByUsernameOrEmail(username, username)
				.orElseThrow(() -> new ApiException("User not found")));
	}

	@Override
	public Boolean update(UserChangeInDTO userIn) {

		return userChangeSubmissionProcessor.process(userIn).isOk();
	}

	@Override
	public Boolean delete(String username) {

		ObjectValidator.notNull("Body can not be null", username);

		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new ApiException("User not found"));

		userRepository.delete(user);

		return userRepository.findByUsername(username).isEmpty();
	}

	@Override
	public void passwordForgotten(HttpServletRequest request, HttpServletResponse response) {
	}

	private String createNewAccessToken(HttpServletRequest request, DecodedJWT decodedJWT) {

		User user = userRepository.findByUsername(decodedJWT.getSubject())
				.orElseThrow(() -> new ApiException("User not found for token refresh"));

		String subject = user.getUsername();
		String issuer = request.getRequestURI();
		List<String> rolesClaim = user.getRoles().stream()
				.map(Role::getName)
				.collect(Collectors.toList());

		String newAccessToken = tokenUtil.generateNewAccessToken(subject, issuer, rolesClaim);

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(user.getUsername(), null, authorities);

		SecurityContextHolder.getContext().setAuthentication(authenticationToken);

		return newAccessToken;
	}
}
