package com.ohmyclass.api.components.preferences.entity;

import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "preferences")
public class Preferences {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "darkmodeEnabled")
	private boolean darkmodeEnabled;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fkUser", referencedColumnName = "id")
	private User fkUser;
}


