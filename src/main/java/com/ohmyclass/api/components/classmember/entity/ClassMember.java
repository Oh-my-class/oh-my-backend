package com.ohmyclass.api.components.classmember.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.group.entity.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import javax.persistence.*;
import java.util.List;

import com.ohmyclass.api.components.user.entity.User;
import com.ohmyclass.api.components.role.entity.Role;

@Getter
@Setter
@Entity
@Table(name = "classmember")
public class ClassMember {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> user;

	@ManyToOne
	@JoinColumn(name = "fkGroup")
	@JsonManagedReference
	private Group fkGroup;

//	@ManyToMany(fetch = FetchType.EAGER)
//	private List<Role> roles;

}
