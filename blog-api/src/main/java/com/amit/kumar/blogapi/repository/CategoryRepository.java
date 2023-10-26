package com.amit.kumar.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amit.kumar.blogapi.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findByCategoryTitle(String categoryTitle);
	
	boolean existsByCategoryTitle(String categoryTitle);
}
