package com.amit.kumar.blogapi.service;

import com.amit.kumar.blogapi.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(int postId, CommentDto commentDto);

	CommentDto updateComment(int commentId, CommentDto commentDto);

	String deleteComment(int commentId);
}
