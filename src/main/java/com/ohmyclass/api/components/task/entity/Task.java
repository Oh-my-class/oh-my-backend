package com.ohmyclass.api.components.task.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.classmember.entity.GroupMember;
import com.ohmyclass.api.components.comment.entity.Comment;
import com.ohmyclass.api.components.group.entity.Group;
import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.tick.entity.Tick;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "dueDate")
	private Date dueDate;

	@Column(name = "createDate")
	private Date createDate;

	@Column(name = "editDate")
	private Date editDate;

	@OneToOne
	@JoinColumn(name = "groupMember")
	@JsonManagedReference
	private GroupMember groupMember;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "subject")
	@JsonManagedReference
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "user")
	@JsonManagedReference
	private User user;

	@ManyToOne
	@JoinColumn(name = "group")
	@JsonManagedReference
	private Group group;

	@OneToMany
	@JoinColumn(name = "tick")
	@JsonManagedReference
	private Set<Tick> ticks;
}
