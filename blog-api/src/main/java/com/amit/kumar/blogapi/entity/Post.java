package com.amit.kumar.blogapi.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@NotBlank(message = "title should not blank")
	@Column(nullable = false)
	private String title;
	@NotBlank(message = "content should not blank")
	@Size(max = 500,message = "Post can not contain more then 500 characters")
	@Column(nullable = false,length = 500)
	private String content;
	private String imageUrl;
	@CreationTimestamp
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)	
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)	
	private Category category;
}
