package com.ohmyclass.api.components.classmember.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.group.entity.Group;
import com.ohmyclass.api.components.task.entity.Task;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "groupmember")
public class GroupMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<User> user;

	@OneToOne
	@JoinColumn(name = "task")
	@JsonManagedReference
	private Task task;

	@ManyToOne
	@JoinColumn(name = "group")
	@JsonManagedReference
	private Group group;


	// @ManyToMany(fetch = FetchType.LAZY)
	// private List<Role> roles;

}
