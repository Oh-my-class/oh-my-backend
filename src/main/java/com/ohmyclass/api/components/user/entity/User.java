package com.ohmyclass.api.components.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.classmember.entity.GroupMember;
import com.ohmyclass.api.components.comment.entity.Comment;
import com.ohmyclass.api.components.preferences.entity.Preferences;
import com.ohmyclass.api.components.role.entity.Role;
import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.task.entity.Task;
import com.ohmyclass.api.components.tick.entity.Tick;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comments", referencedColumnName = "id")
	private List<Comment> comments;

	@OneToOne(mappedBy = "user")
	private Preferences preferences;

	@ManyToMany
	@JoinColumn(name = "classMember")
	@JsonManagedReference
	private Set<GroupMember> classMembers;

	@ManyToMany
	@JoinColumn(name = "subjects")
	@JsonManagedReference
	private Set<Subject> subjects;

	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "user")
	@JsonBackReference
	private Set<Task> tasks;

	@OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "user")
	@JsonBackReference
	private List<Role> roles;

	@OneToOne(mappedBy = "user")
	private Tick tick;

	public void addRole(Role role) {
		if (roles == null)
			roles = new ArrayList<>();

		role.setUser(this);
		roles.add(role);
	}

	public void removeRole(Role role) {
		roles.remove(role);
		role = null;
	}
}
