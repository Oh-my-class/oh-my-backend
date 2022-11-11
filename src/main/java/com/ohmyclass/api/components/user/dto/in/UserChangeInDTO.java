package com.ohmyclass.api.components.user.dto.in;

import com.ohmyclass.api.util.communication.InDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserChangeInDTO extends InDTO {

	private String newEmail;

	private String newPassword;
}
