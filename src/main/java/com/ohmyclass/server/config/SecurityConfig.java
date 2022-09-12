package com.ohmyclass.server.config;

import com.ohmyclass.api.util.ApiConst;
import com.ohmyclass.security.filter.AuthenticationFilter;
import com.ohmyclass.security.filter.AuthorizationFilter;
import com.ohmyclass.security.filter.CustomHttpFilter;
import com.ohmyclass.security.inteceptor.SecurityAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityAuthenticationProvider authProvider;

	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	private AuthorizationFilter authorizationFilter;

	@Override //TODO Switch hardcoded stuff
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.httpBasic()
				.and()
			.csrf().disable();

		http
				.addFilterAfter(authenticationFilter, HttpFilter.class);
//				.addFilterAfter(authorizationFilter, HttpFilter.class);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}
}
