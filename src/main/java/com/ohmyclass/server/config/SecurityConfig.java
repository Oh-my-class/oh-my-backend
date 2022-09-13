package com.ohmyclass.server.config;

import com.ohmyclass.api.util.ApiConst;
import com.ohmyclass.security.filter.AuthenticationFilter;
import com.ohmyclass.security.filter.AuthorizationFilter;
import com.ohmyclass.security.filter.CustomHttpFilter;
import com.ohmyclass.security.inteceptor.SecurityAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final SecurityAuthenticationProvider authProvider;

	private final AuthenticationFilter authenticationFilter;

	private final AuthorizationFilter authorizationFilter;

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/user/login")
				.antMatchers("/user/register");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.httpBasic();

		http.csrf().disable()
				.addFilterAfter(authenticationFilter, BasicAuthenticationFilter.class)
				.addFilterAfter(authorizationFilter, AuthenticationFilter.class);
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
