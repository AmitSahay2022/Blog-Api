package com.amit.kumar.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amit.kumar.blogapi.payloads.CategoryDto;
import com.amit.kumar.blogapi.service.CategoryService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(categoryService.saveCategory(categoryDto), HttpStatus.CREATED);
	}
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable int id){
		return new ResponseEntity<String>(categoryService.daleteCategory(id),HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> findById(@PathVariable int id){
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(id),HttpStatus.OK);
	}
	@GetMapping("/title")
	public ResponseEntity<CategoryDto> findByTitle(@RequestParam String title){
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryByTitle(title),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(),HttpStatus.OK);
	}
}
