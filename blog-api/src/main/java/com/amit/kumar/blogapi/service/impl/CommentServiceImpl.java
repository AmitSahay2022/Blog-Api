package com.amit.kumar.blogapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.amit.kumar.blogapi.entity.Comment;
import com.amit.kumar.blogapi.entity.Post;
import com.amit.kumar.blogapi.entity.User;
import com.amit.kumar.blogapi.exception.ResourceNotFoundException;
import com.amit.kumar.blogapi.payloads.CommentDto;
import com.amit.kumar.blogapi.payloads.PostDto;
import com.amit.kumar.blogapi.payloads.UserDto;
import com.amit.kumar.blogapi.repository.CommentRepository;
import com.amit.kumar.blogapi.service.CommentService;
import com.amit.kumar.blogapi.service.PostService;
import com.amit.kumar.blogapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	private CommentRepository commentRepository;
	private ModelMapper modelMapper;
	private UserService userService;
	private PostService postService;

	@Override
	public CommentDto createComment(int postId, CommentDto commentDto) {
		// To make a Comment a user should exist
		UserDto userDto = userService.getUserById();
		// To make a comment, post should also exist
		PostDto postDto = postService.getPostByPostId(postId);
		// Convert Dtos to Entity
		User user = modelMapper.map(userDto, User.class);
		Post post = modelMapper.map(postDto, Post.class);
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setUser(user);
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public CommentDto updateComment(int commentId, CommentDto commentDto) {
		// TODO Auto-generated method stub
		UserDto userDto = userService.getUserById();
		Comment comment = getCommentByUserIdAndId(userDto.getId(), commentId);
		comment.setContent(commentDto.getContent());
		Comment savedComment = commentRepository.save(comment);
		return modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public String deleteComment( int commentId) {
		// TODO Auto-generated method stub
		UserDto userDto = userService.getUserById();
		
		Comment comment = getCommentByUserIdAndId(userDto.getId(), commentId);
		commentRepository.delete(comment);
		return "Comment Deleted Successfully";
	}
	
	private Comment getCommentByUserIdAndId(long userId, int commentId) {
		Comment comment = commentRepository.
		        findByUserIdAndId(userId, commentId)
		        .orElseThrow(()->new ResourceNotFoundException("Comment not found"));
		return comment;
	}

}
