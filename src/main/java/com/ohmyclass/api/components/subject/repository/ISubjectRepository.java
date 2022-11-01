package com.ohmyclass.api.components.subject.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ohmyclass.api.components.group.entity.Group;
import com.ohmyclass.api.components.subject.entity.Subject;

public interface ISubjectRepository extends CrudRepository<Subject, Long> {

    Optional<Subject> findSubjectById(Long id);

    Optional<Subject> findSubjectByGroupId(Long id);
    
}
