package com.ohmyclass.api.components.user.service.mapper;

import com.ohmyclass.api.components.preferences.service.mapper.APreferencesMapper;
import com.ohmyclass.api.components.user.dto.in.UserInDTO;
import com.ohmyclass.api.components.user.dto.out.UserOutDTO;
import com.ohmyclass.api.components.user.entity.User;
import com.sun.istack.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = APreferencesMapper.class)
public abstract class AUserMapper {

	@Mapping(source = "preferences", target = "preferencesOut")
	public abstract UserOutDTO entityToOutDTO(@NotNull User user);

	public abstract User inDTOToEntity(UserInDTO userIn);
}
