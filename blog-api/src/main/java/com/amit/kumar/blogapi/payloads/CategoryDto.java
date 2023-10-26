package com.amit.kumar.blogapi.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {
	private int categoryId;
	@NotBlank(message = "Title should not blank")
	private String categoryTitle;
}
