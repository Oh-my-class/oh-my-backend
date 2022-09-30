package com.ohmyclass.api.components.user.controller;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.ApiConst;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.util.other.Development;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConst.URL_USER)
public interface IUserController {

	@PutMapping(ApiConst.REGISTER)
	Response<String> register(@RequestBody UserInDTO user);

	@Development
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(ApiConst.GET)
	Response<UserOutDTO> getUser(UserInDTO user);

	@PostMapping(ApiConst.UPDATE)
	Response<UserOutDTO> updateUser(UserChangeInDTO user);

	@DeleteMapping(ApiConst.DELETE)
	@Secured("Role_USER")
	Response<Boolean> deleteUser(UserInDTO user);

	@PutMapping(ApiConst.PW_FORGOTTEN)
	Response<UserOutDTO> passwordForgotten(UserInDTO user);
}
