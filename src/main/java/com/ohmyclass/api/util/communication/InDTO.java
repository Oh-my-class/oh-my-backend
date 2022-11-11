package com.ohmyclass.api.util.communication;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class InDTO {

	private Map<String, String> headers;

	public String resolveHeaderById(String id) {
		return headers.get(id);
	}
}
