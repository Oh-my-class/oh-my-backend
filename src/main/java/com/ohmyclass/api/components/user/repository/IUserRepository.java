package com.ohmyclass.api.components.user.repository;

import com.ohmyclass.api.components.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

	Optional<User> findUserById(Long id);
}
