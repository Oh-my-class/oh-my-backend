package com.ohmyclass.api.components.classmember.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ohmyclass.api.components.classmember.controller.impl.ClassMember;

public interface IClassMemberRepository extends CrudRepository<ClassMember, Long> {

    Optional<ClassMember> findClassMemberById(Long id);

}
