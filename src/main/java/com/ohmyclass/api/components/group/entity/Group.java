package com.ohmyclass.api.components.group.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ohmyclass.api.components.classmember.controller.impl.ClassMember;
import com.ohmyclass.api.components.role.entity.Role;
import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.task.entity.Task;

import javax.persistence.FetchType;

import lombok.Getter;
import lombok.Setter;

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

	@OneToMany(fetch = FetchType.EAGER)
	private Set<ClassMember> classmembers;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Subject> subjects;

	@OneToMany(fetch = FetchType.LAZY)
	private Task tasks;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role>  roles;
    
}

