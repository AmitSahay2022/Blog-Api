package com.amit.kumar.blogapi.payloads;

import com.amit.kumar.blogapi.entity.User;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
	private int id;
	@NotBlank(message = "comment should not blank")
	private String content;
	public User user;
}
