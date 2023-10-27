package com.amit.kumar.blogapi.service;

import java.util.List;

import com.amit.kumar.blogapi.payloads.PostDto;

public interface PostService {
	PostDto savePost(long userId, int categoryId, PostDto postDto);

	PostDto updatePost(long userId,int postId, PostDto postDto);

	String deletePost(long userId,int postId);

	PostDto getPostByPostId(long userId,int postId);
	
	//We are adding a new method to extract post by it's id so any user can comment on it
	PostDto getPostByPostId(int postId);

	List<PostDto> getAllPostByUserId(long userId);

	List<PostDto> getAllPostByCategoryId(int categoryId);

	List<PostDto> getAllPostByCategoryTitle(String title);
}
