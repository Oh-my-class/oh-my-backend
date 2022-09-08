package com.ohmyclass.api.components.user.service.crud;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.util.other.Development;

public interface IUserService {

	Response<UserOutDTO> login(UserInDTO user);

	Response<UserOutDTO> register(UserInDTO user);

	@Development
	Response<UserOutDTO> getUser(UserInDTO user);

	Response<UserOutDTO> update(UserChangeInDTO user);

	Response<Boolean> delete(UserInDTO user);

	Response<UserOutDTO> passwordForgotten(UserInDTO user);
}
