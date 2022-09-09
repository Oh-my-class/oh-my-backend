package com.ohmyclass.api.components.user.controller;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.ApiConst;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.util.other.Development;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConst.URL_ACCOUNT)
public interface IUserController {

	@Secured("USER")
	@PostMapping("/login")
	Response<UserOutDTO> login(UserInDTO user);

	@PostMapping(ApiConst.GET)
	Response<UserOutDTO> register(UserInDTO user);

	@Development
	@PostMapping("/get")
	Response<UserOutDTO> getUser(UserInDTO user);

	@PostMapping(ApiConst.GET)
	Response<UserOutDTO> updateUser(UserChangeInDTO user);

	@PostMapping(ApiConst.GET)
	Response<Boolean> deleteUser(UserInDTO user);

	@PostMapping(ApiConst.GET)
	Response<UserOutDTO> passwordForgotten(UserInDTO user);
}
