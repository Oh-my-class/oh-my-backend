package com.ohmyclass.server.config;

import com.ohmyclass.security.filter.AuthenticationFilter;
import com.ohmyclass.security.filter.AuthorizationFilter;
import com.ohmyclass.security.inteceptor.SecurityAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final SecurityAuthenticationProvider authProvider;

	private final AuthenticationFilter authenticationFilter;

	private final AuthorizationFilter authorizationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.antMatcher("/user")
					.authorizeRequests()
						.antMatchers("/login").permitAll()
						.antMatchers("/register").permitAll()
					.anyRequest().authenticated()
					.and()
				.httpBasic()
					.and()
					.addFilterAfter(authenticationFilter, BasicAuthenticationFilter.class);
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
