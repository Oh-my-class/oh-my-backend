package com.ohmyclass.api.components.user.service.crud;

import com.ohmyclass.api.components.user.dto.in.UserChangeInDTO;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.in.UserRegistrationInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;

public interface IUserService {

	UserOutDTO login(UserInDTO user);

	UserOutDTO register(UserRegistrationInDTO user);

	UserOutDTO getUser(UserInDTO user);

	UserOutDTO update(UserChangeInDTO user);

	UserOutDTO delete(UserInDTO user);

	UserOutDTO passwordForgotten(UserInDTO user);

}
