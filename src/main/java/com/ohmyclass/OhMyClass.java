package com.ohmyclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		prePostEnabled = true
)

public class OhMyClass {

	public static void main(String[] args) {
		SpringApplication.run(OhMyClass.class, args);
	}
}

@Component
class EndpointsListener {

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		applicationContext.getBean(RequestMappingHandlerMapping.class)
				.getHandlerMethods().forEach((key, value) -> System.out.println(key + " - " + value));
	}
}