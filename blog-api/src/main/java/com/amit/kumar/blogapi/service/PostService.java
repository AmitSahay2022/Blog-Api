package com.amit.kumar.blogapi.service;

import java.util.List;

import com.amit.kumar.blogapi.payloads.PostDto;

public interface PostService {
	PostDto savePost(long userId, int categoryId, PostDto postDto);

	PostDto updatePost(int postId, PostDto postDto);

	String deletePost(int postId);

	PostDto getPostByPostId(int postId);

	List<PostDto> getAllPostByUserId(long userId);

	List<PostDto> getAllPostByCategoryId(int categoryId);

	List<PostDto> getAllPostByCategoryTitle(String title);
}
