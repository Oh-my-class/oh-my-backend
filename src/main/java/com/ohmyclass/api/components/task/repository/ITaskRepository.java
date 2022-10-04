package com.ohmyclass.api.components.task.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohmyclass.api.components.group.entity.Group;
import com.ohmyclass.api.components.task.entity.Task;

@Repository
public interface ITaskRepository extends CrudRepository<Task, Long> {

	Optional<Task> findTaskById(Long id);
	
	Optional<Task> getTaskByGroup(Group group);

    Optional<Task> findTaskByDate(Date Date);
	

}
