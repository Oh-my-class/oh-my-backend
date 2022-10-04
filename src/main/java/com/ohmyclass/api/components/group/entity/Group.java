package com.ohmyclass.api.components.group.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ohmyclass.api.components.classmember.entity.ClassMember;
import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.task.entity.Task;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

	@OneToMany(fetch = FetchType.EAGER)
	private Set<ClassMember> classmembers;

	@ManyToOne(fetch = FetchType.EAGER)
	private Set<Subject> subjects;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkGroup")
	@JsonBackReference
	private Set<Task> tasks;

//	@ManyToMany(fetch = FetchType.EAGER)
//	private Set<Role>  roles;
    
}
