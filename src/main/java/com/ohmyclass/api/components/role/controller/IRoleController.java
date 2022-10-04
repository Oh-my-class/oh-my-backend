package com.ohmyclass.api.components.role.controller;

import com.ohmyclass.api.components.role.dto.in.RoleInDTO;
import com.ohmyclass.api.util.communication.Request;
import com.ohmyclass.api.util.communication.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public interface IRoleController {

	Response<Boolean> createRole(@RequestBody Request<RoleInDTO> roleInPayload);

	Response<Boolean> readRole(@RequestBody Request<Long> roleIdPayload);

	Response<Boolean> updateRole(@RequestBody Request<RoleInDTO> updatedRoleInPayload);

	Response<Boolean> deleteRole(@RequestBody Request<Long> roleIdPayload);
}
