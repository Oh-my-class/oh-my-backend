package com.ohmyclass.api.components.user.controller;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public interface IUserController {

	@PostMapping("/auth/register")
	@Operation(summary = "Register a user into the database")
	@ApiResponse(responseCode = "200", description = "The generated", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	Response<Map<String, String>> register(@RequestBody UserInDTO registration);

	@PostMapping("/auth/refresh")
	@Operation(summary = "Refresh the access token")
	@ApiResponse(responseCode = "200", description = "The updated token", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	void refreshToken(HttpServletRequest request, HttpServletResponse response);

	@Secured("ROLE_USER")
	@PutMapping("/auth/password-forgotten")
	@Operation(summary = "Request a URL to change the password")
	@ApiResponse(responseCode = "200", description = "Email has been sent", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutDTO.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	void passwordForgotten(HttpServletRequest request, HttpServletResponse response);

	@Secured("ROLE_ADMIN")
	@GetMapping("/user/{username}")
	@Operation(summary = "Get a user from the database")
	@ApiResponse(responseCode = "200", description = "User", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutDTO.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	Response<UserOutDTO> getUser(@PathVariable String username);

	@Secured("ROLE_USER")
	@PutMapping("/user")
	@Operation(summary = "Edit user details in database")
	@ApiResponse(responseCode = "200", description = "The updated user", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserOutDTO.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	Response<UserOutDTO> updateUser(@RequestBody Request<UserChangeInDTO> userChangeIn);

	@Secured("ROLE_USER")
	@DeleteMapping("/user")
	@Operation(summary = "Delete a user from the database")
	@ApiResponse(responseCode = "200", description = "The success of the operation", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	Response<Boolean> deleteUser(@RequestBody Request<UserInDTO> user);
}
