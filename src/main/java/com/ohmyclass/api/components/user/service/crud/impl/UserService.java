package com.ohmyclass.api.components.user.service.crud.impl;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.in.UserRegistrationInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.service.crud.IUserService;
import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService {

	@Override
	public UserOutDTO login(UserInDTO user) {
		return null;
	}

	@Override
	public UserOutDTO register(UserRegistrationInDTO user) {
		return null;
	}

	@Override
	public UserOutDTO getUser(UserInDTO user) {
		return null;
	}

	@Override
	public UserOutDTO update(UserChangeInDTO user) {
		return null;
	}

	@Override
	public UserOutDTO delete(UserInDTO user) {
		return null;
	}

	@Override
	public UserOutDTO passwordForgotten(UserInDTO user) {
		return null;
	}
}
