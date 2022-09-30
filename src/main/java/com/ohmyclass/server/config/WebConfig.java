package com.ohmyclass.server.config;

import com.ohmyclass.security.inteceptor.AccessInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "PUT", "POST", "DELETE")
				.maxAge(3600);
	}

/*	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app/**")
				.addResourceLocations("/static/app/");
	}*/

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(handlerInterceptor()).addPathPatterns("/**");
	}

	@Bean
	public HandlerInterceptor handlerInterceptor() {
		return new AccessInterceptor();
	}
}
