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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

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
	@JoinColumn(name = "fkGroupMember")
	@JsonManagedReference
	private GroupMember groupMember;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "fkSubject")
	@JsonManagedReference
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "fkGroup")
	@JsonManagedReference
	private Group group;

	@OneToOne
	@JoinColumn(name = "fkTick")
	@JsonManagedReference
	private Tick tick;
}
