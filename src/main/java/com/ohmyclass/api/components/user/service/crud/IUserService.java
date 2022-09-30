package com.ohmyclass.api.components.user.service.crud;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserService {

	Response<String> register(UserInDTO user);

	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	Response<UserOutDTO> getUser(String username);

	Response<UserOutDTO> update(UserChangeInDTO user);

	Response<Boolean> delete(UserInDTO user);

	Response<UserOutDTO> passwordForgotten(HttpServletRequest request, HttpServletResponse response);
}
