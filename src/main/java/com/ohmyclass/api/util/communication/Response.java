package com.ohmyclass.api.util.communication;

import com.ohmyclass.api.util.validation.ValidationResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Response<T> {

	private T t;

	private ValidationResult validationResult = ValidationResult.Ok();

	public Response(T t) {
		this.t = t;
	}
}