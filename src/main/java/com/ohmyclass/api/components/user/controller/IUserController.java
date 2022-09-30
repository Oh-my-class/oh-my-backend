package com.ohmyclass.api.components.user.controller;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Response;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public interface IUserController {

	@PutMapping("/register")
	Response<String> register(@RequestBody UserInDTO user);

	@PostMapping("/refresh-token")
	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	@PutMapping("/password-forgotten")
	Response<UserOutDTO> passwordForgotten(@RequestBody UserInDTO user);

	@PostMapping("/user/get")
	Response<UserOutDTO> getUser(@RequestBody String username);

	@PostMapping("/user/update")
	Response<UserOutDTO> updateUser(@RequestBody UserChangeInDTO user);

	@Secured("Role_USER")
	@DeleteMapping("/user/delete")
	Response<Boolean> deleteUser(@RequestBody UserInDTO user);
}
