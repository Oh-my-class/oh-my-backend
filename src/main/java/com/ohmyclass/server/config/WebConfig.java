package com.ohmyclass.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
				.allowedOriginPatterns("*").allowedHeaders("Authorization", "Cache-Control", "Content-Type")
				.maxAge(3600);
	}
}
