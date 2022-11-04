package com.ohmyclass.api.components.role.controller.impl;

import com.ohmyclass.api.components.role.controller.IRoleController;
import com.ohmyclass.api.components.role.dto.in.RoleInDTO;
import com.ohmyclass.api.components.role.entity.Role;
import com.ohmyclass.api.components.role.repository.IRoleRepository;
import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.IUserRepository;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class RoleController implements IRoleController {

	@Override
	public Response<Boolean> createRole(Request<RoleInDTO> roleInPayload) {
		return null;
	}

	@Override
	public Response<Boolean> readRole(Request<Long> roleIdPayload) {
		return null;
	}

	@Override
	public Response<Boolean> updateRole(Request<RoleInDTO> updatedRoleInPayload) {
		return null;
	}

	@Override
	public Response<Boolean> deleteRole(Request<Long> roleIdPayload) {
		return null;
	}

	private final IRoleRepository roleRepository;
	private final IUserRepository userRepository;

	@Override
	public Response<Boolean> setRoleToUser(String username) {

		Optional<User> user = userRepository.findByUsername(username);

		Role role = new Role();
		role.setFkUser(user.get());
		role.setName("ROLE_ADMIN");

		return Response.ok(roleRepository.save(role) != null);
	}
}
