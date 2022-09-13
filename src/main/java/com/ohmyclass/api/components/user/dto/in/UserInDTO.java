package com.ohmyclass.api.components.user.dto.in;

import com.ohmyclass.api.util.communication.InDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInDTO extends InDTO {

	private String username;

	private String email;

	private String password;
}
