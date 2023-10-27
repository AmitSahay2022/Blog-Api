package com.amit.kumar.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amit.kumar.blogapi.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Optional<Comment> findByUserIdAndId(long userId, int commentId);
}
