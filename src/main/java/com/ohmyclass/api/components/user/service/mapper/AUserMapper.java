package com.ohmyclass.api.components.user.service.mapper;

import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AUserMapper {

	abstract UserOutDTO entityToOutDTO(User user);

	abstract User inDtoToEntity(UserInDTO user);
}
