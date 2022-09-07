package com.ohmyclass.api.components.user.controller.impl;

import com.ohmyclass.api.components.user.controller.IUserController;
import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.in.UserRegistrationInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.service.crud.IUserService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class UserController implements IUserController {

	private final IUserService userService;

	@Override
	public UserOutDTO login(UserInDTO user) {
		return userService.login(user);
	}

	@Override
	public UserOutDTO register(UserRegistrationInDTO registration) {
		return userService.register(registration);
	}

	@Override
	public UserOutDTO getUser(UserInDTO user) {
		return userService.getUser(user);
	}

	@Override
	public UserOutDTO updateUser(UserChangeInDTO userChangeIn) {
		return userService.update(userChangeIn);
	}

	@Override
	public UserOutDTO deleteUser(UserInDTO user) {
		return userService.delete(user);
	}

	@Override
	public UserOutDTO passwordForgotten(UserInDTO user) {
		return userService.passwordForgotten(user);
	}
}
