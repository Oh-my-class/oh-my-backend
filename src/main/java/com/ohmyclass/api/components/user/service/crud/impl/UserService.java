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
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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

		List<String> roles =
				persistedUser.getRoles().stream().map(Role::getName).collect(Collectors.toList());

		return tokenUtil.generateNewTokenMap(persistedUser.getUsername(), "Registration", roles);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {

		String authorizationHeader = request.getHeader(AUTHORIZATION);

		tokenUtil.validateBearer(authorizationHeader);

		DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);

		tokenUtil.validateTokenExpiration(decodedJWT);

		User user = userRepository.findByUsername(decodedJWT.getSubject())
				.orElseThrow(() -> new ApiException("User not found from token refresh"));

		String newAccessToken = tokenUtil.generateNewAccessToken(user);

		try {
			new ObjectMapper().writeValue(response.getOutputStream(), newAccessToken);
		} catch (Exception e) {
			throw new ApiException("Refreshing token failed");
		}

		tokenUtil.addNewTokenToSecurity(user);
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
	public void passwordForgotten(HttpServletRequest request, HttpServletResponse response) {}
}
