package com.amit.kumar.blogapi.service;

import com.amit.kumar.blogapi.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(long userId, int postId, CommentDto commentDto);

	CommentDto updateComment(long userId, int commentId, CommentDto commentDto);

	String deleteComment(long userId, int commentId);
}
