package com.ohmyclass.api.components.role.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "authorities")
	private List<GrantedAuthority> authorities;

	@ManyToOne
	@JoinColumn(name = "fkUser")
	@JsonManagedReference
	private User fkUser;
}
