package com.amit.kumar.blogapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.kumar.blogapi.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUserId(long id);

	List<Post> findByCategoryCategoryId(int categoryId);
	
	List<Post> findByCategoryCategoryTitle(String title);
}
