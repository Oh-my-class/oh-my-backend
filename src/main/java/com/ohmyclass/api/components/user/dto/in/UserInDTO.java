package com.ohmyclass.api.components.user.dto.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ohmyclass.api.util.communication.InDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInDTO extends InDTO {

	private String username;

	private String email;

	private String password;
}
