package com.ohmyclass.security;

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
public class DatabaseUserDetailsService implements UserDetailsService {

	private final IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> potentialUser = userRepo.findUserByUsernameOrEmail(username, null);

		if (potentialUser.isEmpty()) // Guard
			throw new IllegalArgumentException("User not found in database");

		User user = potentialUser.get();

		if (user.getRoles().isEmpty()) // Guard
			throw new IllegalArgumentException("User lacks authorities/has no roles");

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.flatMap(role -> role.getAuthorities().stream())
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		authorities.forEach(System.out::println);

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}
}
