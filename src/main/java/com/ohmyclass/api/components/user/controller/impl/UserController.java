package com.ohmyclass.api.components.user.controller.impl;

import com.ohmyclass.api.components.user.controller.IUserController;
import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;

import com.ohmyclass.api.components.user.service.crud.impl.UserService;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class UserController implements IUserController {

	private final UserService userService;

	@Override
	public Response<String> register(UserInDTO registration) {
		return userService.register(registration);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		userService.refreshToken(request, response);
	}

	@Override
	public Response<UserOutDTO> passwordForgotten(HttpServletRequest request, HttpServletResponse response) {
		return userService.passwordForgotten(request, response);
	}

	@Override
	public Response<UserOutDTO> getUser(@RequestBody Request<String> usernamePayload) {
		return userService.getUser(usernamePayload.getData());
	}

	@Override
	public Response<UserOutDTO> updateUser(UserChangeInDTO userChangeIn) {
		return userService.update(userChangeIn);
	}

	@Override
	public Response<Boolean> deleteUser(UserInDTO user) {
		return userService.delete(user);
	}
}
