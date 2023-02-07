package com.ohmyclass.api.components.group.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.classmember.entity.GroupMember;
import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.task.entity.Task;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "group")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<GroupMember> groupMembers;

	@OneToMany(fetch = FetchType.LAZY)
	private Set<Subject> subjects;

	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "group")
	@JsonBackReference
	private Set<Task> tasks;

	@ManyToOne
	@JoinColumn(name = "user")
	@JsonManagedReference
	private User user;

	// @ManyToMany(fetch = FetchType.LAZY)
	// private Set<Role> roles;

}
