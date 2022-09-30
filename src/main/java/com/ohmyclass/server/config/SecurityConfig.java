package com.ohmyclass.server.config;

import com.ohmyclass.security.util.JwtAuthenticationEntryPoint;
import com.ohmyclass.security.filters.JwtAuthenticationFilter;
import com.ohmyclass.security.filters.JwtAuthorizationFilter;
import com.ohmyclass.security.services.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] AUTH_WHITELIST = {
		"/api/login/**",
		"/user/register/**",
	};

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtUserDetailsService jwtUserDetailsService;

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	private final JwtAuthorizationFilter jwtAuthorizationFilter;


	@Override
	protected void configure(HttpSecurity http) throws Exception {

		jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
		jwtAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());

		http.csrf().disable()
				.sessionManagement()
					.sessionCreationPolicy(STATELESS)
					.and()
				.exceptionHandling()
					.authenticationEntryPoint(jwtAuthenticationEntryPoint)
					.and()
				.antMatcher("/")
					.authorizeRequests()
						.antMatchers(AUTH_WHITELIST).permitAll()
						.anyRequest().authenticated()
					.and()
				.addFilter(jwtAuthenticationFilter)
				.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
