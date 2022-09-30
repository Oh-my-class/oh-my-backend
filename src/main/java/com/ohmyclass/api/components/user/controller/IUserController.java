package com.ohmyclass.api.components.user.controller;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1")
public interface IUserController {

	@PutMapping("/auth/register")
	Response<String> register(@RequestBody UserInDTO user);

	@PostMapping("/auth/refresh")
	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	@Secured("ROLE_ADMIN")
	@PutMapping("/auth/password-forgotten")
	Response<UserOutDTO> passwordForgotten(HttpServletRequest request, HttpServletResponse response);

	@GetMapping("/user/get")
	Response<UserOutDTO> getUser(@RequestBody Request<String> usernamePayload);

	@PostMapping("/user/update")
	Response<UserOutDTO> updateUser(@RequestBody UserChangeInDTO user);

	@Secured("Role_USER")
	@DeleteMapping("/user/delete")
	Response<Boolean> deleteUser(@RequestBody UserInDTO user);
}
