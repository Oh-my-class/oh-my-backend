package com.ohmyclass.api.components.role.repository;

import com.ohmyclass.api.components.role.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends CrudRepository<Role, Long> {

//	Boolean saveOrUpdate(Role role);
}
