package com.ohmyclass.api.components.comment.entity;

import com.ohmyclass.api.components.user.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "content")
	private String content;

	@Column(name = "date")
	private Date date;

	@OneToOne (mappedBy = "fkParentComment")
	private Comment fkParentComment;

	@ManyToOne(fetch = FetchType.LAZY)
	private User fkUser;


}
