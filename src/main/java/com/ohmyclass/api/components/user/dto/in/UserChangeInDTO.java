package com.ohmyclass.api.components.user.dto.in;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangeInDTO extends UserInDTO {

	private String newEmail;

	private String newPassword;
}
