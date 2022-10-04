package com.ohmyclass.api.components.comment.dto.out;

import com.ohmyclass.api.components.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class CommentOutDTO {
	private Long id;

	private String content;

	private Set<CommentOutDTO> children;

	private String username;

	private Date date;
}
