package com.ohmyclass.server.config;

import com.ohmyclass.api.util.ApiConst;
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
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityAuthenticationProvider authProvider;

	@Override //TODO Switch hardcoded stuff
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll() // ! Only keep if necessary
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage(ApiConst.URL_ACCOUNT + "/login")
				.permitAll()
				.and()
			.formLogin()
				.loginPage("/register")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.httpBasic();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//TODO Make work with custom roles
		PasswordEncoder encoder = bCryptPasswordEncoder();
		auth.authenticationProvider(authProvider);
	}
}
