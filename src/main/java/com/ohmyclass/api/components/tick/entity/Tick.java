package com.ohmyclass.api.components.tick.entity;

import com.ohmyclass.api.components.task.entity.Task;
import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tick")
public class Tick {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	private Task task;

	@OneToOne(fetch = FetchType.EAGER)
	private User user;
}
