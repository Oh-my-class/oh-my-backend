package com.ohmyclass.security.service;

import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> potentialUser = userRepo.findUserByUsernameOrEmail(username, null);

		// Guard
		if (potentialUser.isEmpty()) {

			throw new IllegalArgumentException("User not found in database");
		}

		User user = potentialUser.get();

		// Guard
		if (user.getRoles().isEmpty()) {

			throw new IllegalArgumentException("User lacks authorities/has no roles");
		}

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.flatMap(role -> role.getAuthorities().stream())
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities != null ? authorities : new ArrayList<>());
	}
}
