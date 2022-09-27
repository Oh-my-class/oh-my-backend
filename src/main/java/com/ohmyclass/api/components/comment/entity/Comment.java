package com.ohmyclass.api.components.comment.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.task.entity.Task;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "content")
	private String content;

	@Column(name = "date")
	private Date date;

	@ManyToOne
	private Comment fkParentComment;

	@OneToMany(mappedBy = "fkParentComment")
	private Set<Comment> children;

	@ManyToOne(fetch = FetchType.LAZY)
	private User fkUser;

	@ManyToOne
	@JoinColumn(name = "fkTask")
	@JsonManagedReference
	private Task fkTasks;

}
