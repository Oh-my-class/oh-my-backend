package com.ohmyclass.api.components.user.dto.out;

import com.ohmyclass.api.components.preferences.dto.out.PreferencesOutDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOutDTO {

	private Long id;

	private String username;

	private PreferencesOutDTO preferencesOut;
}
