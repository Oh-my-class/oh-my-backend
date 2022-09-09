package com.ohmyclass.api.components.user.controller.impl;

import com.ohmyclass.api.components.user.controller.IUserController;
import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.service.crud.IUserService;

import com.ohmyclass.api.util.communication.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
public class UserController implements IUserController {

	private final IUserService userService;

	@Override
	public Response<UserOutDTO> login(@RequestBody UserInDTO user) {
		return userService.login(user);
	}

	@Override
	public Response<UserOutDTO> register(UserInDTO registration) {
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
}
