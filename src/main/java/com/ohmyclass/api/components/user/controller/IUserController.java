package com.ohmyclass.api.components.user.controller;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.in.UserRegistrationInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.URL_ACCOUNT)
public interface IUserController {

	@PostMapping(Constants.GET)
	UserOutDTO login(UserInDTO user);

	@PostMapping(Constants.GET)
	UserOutDTO register(UserRegistrationInDTO user);

	@PostMapping(Constants.GET)
	UserOutDTO getUser(UserInDTO user);

	@PostMapping(Constants.GET)
	UserOutDTO updateUser(UserChangeInDTO user);

	@PostMapping(Constants.GET)
	UserOutDTO deleteUser(UserInDTO user);

	@PostMapping(Constants.GET)
	UserOutDTO passwordForgotten(UserInDTO user);
}
