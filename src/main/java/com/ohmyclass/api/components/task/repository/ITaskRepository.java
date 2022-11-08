package com.ohmyclass.api.components.task.repository;

import com.ohmyclass.api.components.group.entity.Group;
import com.ohmyclass.api.components.task.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface ITaskRepository extends CrudRepository<Task, Long> {

	Optional<Task> findTaskById(Long id);

	Optional<Task> findTaskByGroupId(Long id);

	Optional<Task> findTaskByDate(Date Date);


}
