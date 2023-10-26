package com.amit.kumar.blogapi.service;

import java.util.List;

import com.amit.kumar.blogapi.entity.Category;
import com.amit.kumar.blogapi.payloads.CategoryDto;

public interface CategoryService {
	CategoryDto saveCategory(CategoryDto categoryDto);

	String daleteCategory(int categoryId);

	CategoryDto getCategoryById(int categoryId);

	CategoryDto getCategoryByTitle(String title);

	List<CategoryDto> getAllCategories();
}
