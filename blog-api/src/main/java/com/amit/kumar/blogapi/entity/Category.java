package com.amit.kumar.blogapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	@Column(nullable = false)
	@NotBlank(message = "Title should not blank")
	private String categoryTitle;
}
