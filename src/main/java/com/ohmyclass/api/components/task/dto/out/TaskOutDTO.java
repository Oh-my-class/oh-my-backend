package com.ohmyclass.api.components.task.dto.out;

import com.ohmyclass.api.components.group.dto.out.GroupOutDTO;
import com.ohmyclass.api.components.subject.dto.out.SubjectOutDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskOutDTO {
	private String title;

	private String description;

	private Date dueDate;

	private Date createDate;

	private Date editDate;

	private SubjectOutDTO subject;

	private GroupOutDTO group;
}
