package com.ohmyclass.api.components.group.dto.out;

import com.ohmyclass.api.components.subject.dto.out.SubjectOutDTO;
import com.ohmyclass.api.components.task.dto.out.TaskOutDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GroupOutDTO {

	private Long id;

	private String name;

	private Set<SubjectOutDTO> subjects;

	private Set<TaskOutDTO> tasks;
}
