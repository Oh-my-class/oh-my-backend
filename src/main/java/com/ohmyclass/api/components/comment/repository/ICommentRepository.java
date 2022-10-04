package com.ohmyclass.api.components.comment.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ohmyclass.api.components.comment.entity.Comment;

@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {

    Optional<Comment> findCommentById(Long id);

    Optional<Comment> findCommentOrderByDate(Date date);
}
