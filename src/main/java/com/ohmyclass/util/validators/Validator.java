package com.ohmyclass.util.validators;

import com.ohmyclass.util.validators.collectors.ReasonCollector;
import com.ohmyclass.util.validators.rejectors.BaseRejector;
import com.ohmyclass.util.validators.rejectors.StringRejector;
import org.springframework.stereotype.Component;

@Component
public class Validator {

	public static ReasonCollector reject() {
		return Pass.reject();
	}

	public static BaseRejector<Object> reject(Object entry) {
		return new BaseRejector<>(new Pass(), entry);
	}

	public static StringRejector reject(String entry) {
		return new StringRejector(new Pass(), entry);
	}
}
