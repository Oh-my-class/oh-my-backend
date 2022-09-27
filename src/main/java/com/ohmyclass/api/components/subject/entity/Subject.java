package com.ohmyclass.api.components.subject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.group.entity.Group;
import com.ohmyclass.api.components.task.entity.Task;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "color", length = 6)
	private String color;

	@ManyToMany
	@JoinColumn(name = "fkGroup")
	@JsonManagedReference
	private Set<Group> fkGroups;

	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkSubject")
	@JsonBackReference
	private Set<Task> tasks ;
}
