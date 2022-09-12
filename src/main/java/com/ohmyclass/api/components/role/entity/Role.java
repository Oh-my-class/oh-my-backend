package com.ohmyclass.api.components.role.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@ElementCollection
	@Column(name = "authorities")
	private List<String> authorities;

	@ManyToOne
	@JoinColumn(name = "fkUser")
	private User fkUser;
}
