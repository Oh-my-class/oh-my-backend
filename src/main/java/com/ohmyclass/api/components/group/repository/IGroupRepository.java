package com.ohmyclass.api.components.group.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohmyclass.api.components.group.entity.Group;

@Repository
public interface IGroupRepository extends CrudRepository<Group, Long> {

	Optional<Group> findGroupById(Long id);

	Optional<Group> getAllGroupsByUserId(Long userId);
}
