package com.ohmyclass.api.util.communication;

import com.ohmyclass.api.util.validation.ValidationResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Response<Data> {

	private Data data;

	private ValidationResult validationResult;

	public Response(Data data) {
		this.data = data;
		this.validationResult = ValidationResult.ok();
	}

	public Response(Data data, ValidationResult validationResult) {
		this.data = data;
		this.validationResult = validationResult;
	}
}