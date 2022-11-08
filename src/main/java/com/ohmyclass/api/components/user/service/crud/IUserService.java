package com.ohmyclass.api.components.user.service.crud;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IUserService {

	Map<String, String> register(UserInDTO user);

	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	UserOutDTO getUser(String username);

	UserOutDTO update(Request<UserChangeInDTO> user);

	Boolean delete(Request<UserInDTO> user);

	void passwordForgotten(HttpServletRequest request, HttpServletResponse response);
}
