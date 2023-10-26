package com.amit.kumar.blogapi.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.amit.kumar.blogapi.entity.Category;
import com.amit.kumar.blogapi.exception.ResourceNotFoundException;
import com.amit.kumar.blogapi.payloads.CategoryDto;
import com.amit.kumar.blogapi.repository.CategoryRepository;
import com.amit.kumar.blogapi.service.CategoryService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;
	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		if(categoryRepository.existsByCategoryTitle(categoryDto.getCategoryTitle())) {
			return categoryDto;
		}
		Category category = modelMapper.map(categoryDto, Category.class);
		Category saved = categoryRepository.save(category);
		return modelMapper.map(saved, CategoryDto.class);
	}

	@Override
	public String daleteCategory(int categoryId) {
		// TODO Auto-generated method stub
		CategoryDto categoryById = getCategoryById(categoryId);
		Category category = modelMapper.map(categoryById, Category.class);
		categoryRepository.delete(category);
		return "Category Deleted Successfully";
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository
		          .findById(categoryId)
		          .orElseThrow(()->new ResourceNotFoundException("Category Not Found with id: "+categoryId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryByTitle(String title) {
		// TODO Auto-generated method stub
		Category category = categoryRepository
		      .findByCategoryTitle(title)
		      .orElseThrow(()->new ResourceNotFoundException("Category Not Found with Title: "+title));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		// TODO Auto-generated method stub
		List<Category> listOfCategory = categoryRepository.findAll();
		List<CategoryDto> listOfCategoryDto = listOfCategory.stream().map(i->modelMapper.map(i, CategoryDto.class)).toList();
		return listOfCategoryDto;
	}

}
