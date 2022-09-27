package com.ohmyclass.api.components.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ohmyclass.api.components.classmember.entity.ClassMember;
import com.ohmyclass.api.components.comment.entity.Comment;
import com.ohmyclass.api.components.preferences.entity.Preferences;
import com.ohmyclass.api.components.subject.entity.Subject;
import com.ohmyclass.api.components.task.entity.Task;
import com.ohmyclass.api.components.role.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fkComment", referencedColumnName = "id")
	private List<Comment> comments;

	@OneToOne(mappedBy = "fkUser")
	private Preferences preferences;

	@ManyToMany
	@JoinColumn(name = "fkClassMember")
	@JsonManagedReference
	private Set<ClassMember> fkClassMembers;

	@OneToMany
	@JoinColumn(name ="fkComment")
	@JsonManagedReference
	private Set<Comment> fkComments;

	@OneToOne
	@JoinColumn(name = "fkTask")
	@JsonManagedReference
	private Task fkTask;

	@OneToOne(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkUser")
	@JsonBackReference
	private Task task ;


	@OneToMany(cascade = {CascadeType.ALL},
			orphanRemoval = true,
			mappedBy = "fkUser")
	@JsonBackReference
	private List<Role> roles;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public void create(String username, String email, String password) {
		setUsername(username);
		setEmail(email);
		setPassword(password);
	}
}
