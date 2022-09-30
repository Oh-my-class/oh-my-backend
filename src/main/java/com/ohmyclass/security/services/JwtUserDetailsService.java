package com.ohmyclass.security.services;

import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final User user = userRepo.findByUsername(username);

		// Guard
		if (user == null) {

			log.error("User not found in database");
			throw new UsernameNotFoundException("User not found in database");
		}

		log.info("User found: {}", username);

		List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.
				User(user.getUsername(), user.getPassword(), !authorities.isEmpty() ? authorities : new ArrayList<>());
	}
}
