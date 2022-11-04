package com.ohmyclass.api.components.role.controller;

import com.ohmyclass.api.components.role.dto.in.RoleInDTO;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/role")
public interface IRoleController {

	Response<Boolean> createRole(@RequestBody Request<RoleInDTO> roleInPayload);

	Response<Boolean> readRole(@RequestBody Request<Long> roleIdPayload);

	Response<Boolean> updateRole(@RequestBody Request<RoleInDTO> updatedRoleInPayload);

	Response<Boolean> deleteRole(@RequestBody Request<Long> roleIdPayload);

	@PostMapping("/set-to-user")
	@Operation(summary = "Register a user into the database")
	@ApiResponse(responseCode = "200", description = "The generated", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)) })
	@ApiResponse(responseCode = "401", description = "Authentication failed")
	@ApiResponse(responseCode = "500", description = "General server error")
	Response<Boolean> setRoleToUser(@RequestBody String username);
}
