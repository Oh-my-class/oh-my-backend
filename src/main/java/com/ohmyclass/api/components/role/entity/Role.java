package com.ohmyclass.api.components.role.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

	/*	@ManyToOne
	@JoinColumn(name = "fkGroup")
	@JsonManagedReference
	private Group fkGroup;*/
}
