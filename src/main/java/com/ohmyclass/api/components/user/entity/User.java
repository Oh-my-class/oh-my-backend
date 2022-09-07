package com.ohmyclass.api.components.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	@Id
	private Long id;

	//TODO Implement fields for entity
}
