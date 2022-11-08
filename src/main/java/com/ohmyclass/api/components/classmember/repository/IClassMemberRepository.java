package com.ohmyclass.api.components.classmember.repository;

import java.util.Optional;

import com.ohmyclass.api.components.classmember.entity.ClassMember;
import org.springframework.data.repository.CrudRepository;

public interface IClassMemberRepository extends CrudRepository<ClassMember, Long> {

    Optional<ClassMember> findClassMemberById(Long id);

}
