package com.ohmyclass.api.components.user.service.crud;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.exceptions.ApiException;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.util.Map;

public interface IUserService {

	@Transactional(rollbackOn = ApiException.class)
	Map<String, String> register(UserInDTO user);

	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	UserOutDTO getUser(String username);

	@Transactional(rollbackOn = ApiException.class)
	Boolean update(UserChangeInDTO user);

	@Transactional(rollbackOn = ApiException.class)
	Boolean delete(String username);

	void passwordForgotten(HttpServletRequest request, HttpServletResponse response);
}
