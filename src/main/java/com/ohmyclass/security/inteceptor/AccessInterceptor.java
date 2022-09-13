package com.ohmyclass.security.inteceptor;

import com.ohmyclass.api.util.communication.CreateResponseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Deprecated
@AllArgsConstructor
public class AccessInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		System.out.println("==========AccessInterceptor triggered");

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_OK);

		// Skip guard
		if (urlIsAllowedToSkipAuth(request))
			return true;

		return validate(/* Auth params */) || createFailedResponse(response);
	}

	private boolean urlIsAllowedToSkipAuth(HttpServletRequest request) {
		return Arrays.stream(UrlWhiteList.values())
				.anyMatch(allowedURL -> request.getRequestURI().contains(allowedURL.val));
	}

	private boolean createFailedResponse(HttpServletResponse response) {
		try {
			CreateResponseService.newError(response,
					HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Authentication failed");
		} catch (IOException e) {
			//TODO Log
		}

		return false;
	}

	private boolean validate(/* Auth params */) {
		return true; //TODO validate auth params
	}

	enum UrlWhiteList { // TODO: Move to somewhere else
		LOGIN("user/login"),
		REGISTER("user/register");

		String val;

		UrlWhiteList (String val) {
			this.val = val;
		}
	}
}
