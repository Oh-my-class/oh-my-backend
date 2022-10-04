package com.ohmyclass.api.components.tick.dto.out;

import com.ohmyclass.api.components.task.dto.out.TaskOutDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TickOutDTO {
	private Long id;

	private boolean isDone;

	private TaskOutDTO task;
}
