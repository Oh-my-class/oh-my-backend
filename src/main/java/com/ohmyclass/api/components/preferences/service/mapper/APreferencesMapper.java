package com.ohmyclass.api.components.preferences.service.mapper;

import com.ohmyclass.api.components.preferences.dto.in.PreferencesInDTO;
import com.ohmyclass.api.components.preferences.dto.out.PreferencesOutDTO;
import com.ohmyclass.api.components.preferences.entity.Preferences;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class APreferencesMapper {

	@Mapping(source = "fkUser.id", target = "fkUserId")
	abstract public PreferencesOutDTO entityToOutDTO(Preferences entity);

	@Mapping(source = "fkUserId", target = "fkUser.id")
	abstract public Preferences inDTOToEntity(PreferencesInDTO inDTO);
}
