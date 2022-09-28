package com.ohmyclass.server.config;

import com.ohmyclass.security.blueprint.JwtAuthenticationEntryPoint;
import com.ohmyclass.security.filter.JwtRequestFilter;
import com.ohmyclass.security.inteceptor.SecurityAuthenticationProvider;
import com.ohmyclass.security.service.JwtUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtUserDetailsService jwtUserDetailsService;

	private final JwtRequestFilter jwtRequestFilter;

	private final SecurityAuthenticationProvider authProvider;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
				.exceptionHandling()
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.and()
				.antMatcher("/")
					.authorizeRequests()
						.antMatchers("/login").permitAll()
						.antMatchers("/register").permitAll()
					.anyRequest().authenticated()
					.and()
				.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService)
				.passwordEncoder(bCryptPasswordEncoder());
	}

/*
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
*/

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("");
	}
}
