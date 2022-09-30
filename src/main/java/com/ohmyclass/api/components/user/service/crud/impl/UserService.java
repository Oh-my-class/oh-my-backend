package com.ohmyclass.api.components.user.service.crud.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohmyclass.api.components.role.entity.Role;
import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.IUserRepository;
import com.ohmyclass.api.components.user.service.crud.IUserService;
import com.ohmyclass.api.components.user.service.mapper.AUserMapper;
import com.ohmyclass.api.exceptions.ApiRequestException;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.security.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Log4j2
@Component
@AllArgsConstructor
public class UserService implements IUserService {

	private final IUserRepository userRepo;

	private final AUserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	private final JwtTokenUtil tokenUtil;

	@Override
	public Response<String> register(UserInDTO userIn) {

		return null;
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(AUTHORIZATION);

		if (tokenUtil.isValidBearer(authorizationHeader)) {
			try {
				DecodedJWT decodedJWT = tokenUtil.extractBearer.apply(authorizationHeader);

				User user = userRepo.findByUsername(decodedJWT.getSubject())
						.orElseThrow(() -> new ApiRequestException("User not found"));

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

				new ObjectMapper().writeValue(response.getOutputStream(), newAccessToken);

			} catch (Exception e) {
				throw new ApiRequestException(e.getMessage());
			}

		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}

	@Override
	public Response<UserOutDTO> getUser(String username) {
		return new Response<>(userMapper.entityToOutDTO(userRepo.findByUsername(username)
				.orElseThrow(() -> new ApiRequestException("User not found"))));
	}

	@Override
	public Response<UserOutDTO> update(UserChangeInDTO userIn) {
		return null;
	}

	@Override
	public Response<Boolean> delete(UserInDTO userIn) {
		return null;
	}

	@Override
	public Response<UserOutDTO> passwordForgotten(UserInDTO user) {
		return null;
	}

	private User saveUser(User user) {
		log.info("Saving user {}", user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
}
