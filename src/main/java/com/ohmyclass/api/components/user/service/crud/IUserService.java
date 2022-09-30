package com.ohmyclass.api.components.user.service.crud;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.util.communication.Response;
import com.ohmyclass.api.util.validation.ValidationResult;
import com.ohmyclass.util.other.Development;

public interface IUserService {

	Response<String> register(UserInDTO user);

	Response<UserOutDTO> getUser(UserInDTO user);

	Response<UserOutDTO> update(UserChangeInDTO user);

	Response<Boolean> delete(UserInDTO user);

	Response<UserOutDTO> passwordForgotten(UserInDTO user);
}
