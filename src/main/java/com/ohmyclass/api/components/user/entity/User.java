package com.ohmyclass.api.components.user.entity;

import com.ohmyclass.api.components.preferences.entity.Preferences;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

	@OneToOne(mappedBy = "fkUser")
	private Preferences preferences; //TODO
}
