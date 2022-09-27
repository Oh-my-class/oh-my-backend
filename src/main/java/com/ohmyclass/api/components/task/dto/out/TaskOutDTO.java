package com.ohmyclass.api.components.task.dto.out;

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

	//TODO: subject

	// TODO group
}
