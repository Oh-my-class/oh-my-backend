package com.ohmyclass.api.components.preferences.service.mapper;

import com.ohmyclass.api.components.preferences.dto.in.PreferencesInDTO;
import com.ohmyclass.api.components.preferences.dto.out.PreferencesOutDTO;
import com.ohmyclass.api.components.preferences.entity.Preferences;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class APreferencesMapper {

//	@Mapping(source = "user.id", target = "userId")
	abstract public PreferencesOutDTO entityToOutDTO(Preferences entity);

//	@Mapping(source = "userId", target = "user.id")
	abstract public Preferences inDTOToEntity(PreferencesInDTO inDTO);
}
