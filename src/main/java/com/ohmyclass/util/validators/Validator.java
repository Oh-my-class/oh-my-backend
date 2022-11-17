package com.ohmyclass.util.validators;

import com.ohmyclass.util.validators.collectors.ReasonCollector;
import com.ohmyclass.util.validators.rejectors.BaseRejector;
import com.ohmyclass.util.validators.rejectors.BooleanRejector;
import com.ohmyclass.util.validators.rejectors.IntegerRejector;
import com.ohmyclass.util.validators.rejectors.StringRejector;
import org.springframework.stereotype.Component;

/**
 * Extendable reject-validator. See {@link BaseRejector}
 * @author z-100
 */
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
	
	public static BooleanRejector reject(Boolean entry) {
		return new BooleanRejector(new Pass(), entry);
	}

	public static IntegerRejector reject(Integer entry) {
		return new IntegerRejector(new Pass(), entry);
	}

}
