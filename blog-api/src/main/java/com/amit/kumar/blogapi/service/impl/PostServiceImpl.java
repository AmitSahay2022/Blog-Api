package com.amit.kumar.blogapi.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.amit.kumar.blogapi.entity.Category;
import com.amit.kumar.blogapi.entity.Post;
import com.amit.kumar.blogapi.entity.User;
import com.amit.kumar.blogapi.exception.ResourceNotFoundException;
import com.amit.kumar.blogapi.payloads.CategoryDto;
import com.amit.kumar.blogapi.payloads.PostDto;
import com.amit.kumar.blogapi.payloads.UserDto;
import com.amit.kumar.blogapi.repository.PostRepository;
import com.amit.kumar.blogapi.service.CategoryService;
import com.amit.kumar.blogapi.service.PostService;
import com.amit.kumar.blogapi.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private UserService userService;
	private CategoryService categoryService;
	private ModelMapper modelMapper;
	@Override
	public PostDto savePost(long userId, int categoryId, PostDto postDto) {
		// TODO Auto-generated method stub
		UserDto userDto = userService.getUserById(userId);
		CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
		//Now convert Dtos to Entity
		User user = modelMapper.map(userDto, User.class);
		Category category = modelMapper.map(categoryDto, Category.class);
		Post post = modelMapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = postRepository.save(post);
		return modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(long userId, int postId, PostDto postDto) {
		// TODO Auto-generated method stub
		PostDto postDto2 = getPostByPostId(userId, postId);
		Post post = modelMapper.map(postDto2, Post.class);
		post.setContent(postDto.getContent());
		post.setImageUrl(postDto.getImageUrl());
		post.setTitle(postDto.getTitle());
		Post saved = postRepository.save(post);
		return modelMapper.map(saved, PostDto.class);
	}

	@Override
	public String deletePost(long userId, int postId) {
		// TODO Auto-generated method stub
		PostDto postDto = getPostByPostId(userId, postId);
		Post post = modelMapper.map(postDto, Post.class);
		postRepository.delete(post);
		return "Post Deleted Successfully";
	}

	@Override
	public PostDto getPostByPostId(long userId, int postId) {
		// TODO Auto-generated method stub
		Post post = postRepository
				          .findByUserIdAndPostId(userId, postId).orElseThrow(
				        		  ()->new ResourceNotFoundException("Post not Found"));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getAllPostByUserId(long userId) {
		// TODO Auto-generated method stub
		List<Post> listOfPost = postRepository.findByUserId(userId);
		List<PostDto> postDtos = listOfPost.stream().map(p->modelMapper.map(p, PostDto.class)).toList();
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		List<Post> listOfPost = postRepository.findByCategoryCategoryId(categoryId);
		List<PostDto> list = listOfPost.stream().map(p->modelMapper.map(p, PostDto.class)).toList();
		return list;
	}

	@Override
	public List<PostDto> getAllPostByCategoryTitle(String title) {
		// TODO Auto-generated method stub
		List<Post> listOfPost = postRepository.findByCategoryCategoryTitle(title);
		List<PostDto> postDtos = listOfPost.stream().map(p->modelMapper.map(p, PostDto.class)).toList();
		return postDtos;
	}
	//-----------This method will be used by comment Service so any user can get post and make comments
	@Override
	public PostDto getPostByPostId(int postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not Found"));
		return modelMapper.map(post, PostDto.class);
	}

}
