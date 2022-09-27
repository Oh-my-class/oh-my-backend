package com.ohmyclass.api.components.group.dto.out;

import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.task.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GroupOutDTO {

	private Long id;

	private String name;

	private Set<Subject> subjects;

	private Set<Task> tasks;
}
