package com.amit.kumar.blogapi.payloads;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.amit.kumar.blogapi.entity.Category;
import com.amit.kumar.blogapi.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	private int postId;
	@NotBlank(message = "title should not blank")
	private String title;
	@NotBlank(message = "content should not blank")
	@Size(max = 500, message = "Post can not contain more then 500 characters")
	private String content;
	private String imageUrl;
	@CreationTimestamp
	private Date createdAt;
	private User user;
	private Category category;
	//ModelMapper is  converting List<Comment> to List<CommentDto>
	private List<CommentDto> comments=new ArrayList<>();
}
