package com.ohmyclass.api.components.user.repository;

import com.ohmyclass.api.components.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Optional<User> findByUsernameAndPassword(String username, String password);
}
