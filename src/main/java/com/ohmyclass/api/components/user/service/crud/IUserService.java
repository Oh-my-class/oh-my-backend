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

	Response<Map<String, String>> register(Request<UserInDTO> user);

	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	Response<UserOutDTO> getUser(String username);

	Response<UserOutDTO> update(Request<UserChangeInDTO> user);

	Response<Boolean> delete(Request<UserInDTO> user);

	Response<UserOutDTO> passwordForgotten(HttpServletRequest request, HttpServletResponse response);
}
