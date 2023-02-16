package com.ohmyclass.api.components.role.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

	public Role() {}

	public Role(String name) {
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name")
	private String name;

	@ElementCollection
	@Column(name = "authorities")
	private List<String> authorities;

	@ManyToOne
	@JoinColumn(name = "user")
	@JsonManagedReference
	private User user;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "fkGroup")
	 * 
	 * @JsonManagedReference private Group fkGroup;
	 */
}
