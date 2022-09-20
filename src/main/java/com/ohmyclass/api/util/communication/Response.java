package com.ohmyclass.api.util.communication;

import com.ohmyclass.api.util.validation.ValidationResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Response<DTO> {

	private DTO dto;

	private ValidationResult validationResult = ValidationResult.Ok();

	public Response(DTO DTO) {
		this.dto = DTO;
	}
}