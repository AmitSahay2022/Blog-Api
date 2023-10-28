package com.amit.kumar.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.kumar.blogapi.payloads.CommentDto;
import com.amit.kumar.blogapi.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {
   private CommentService commentService;
  
   @PostMapping("{postId}")
   public ResponseEntity<CommentDto> saveComment(
		                                         @PathVariable int postId,
		                                         @RequestBody CommentDto commentDto){
	   return new ResponseEntity<CommentDto>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
   }
   
   @PutMapping("{commentId}")
   public ResponseEntity<CommentDto> updateComment(
		                                           @PathVariable int commentId,
		                                           @RequestBody CommentDto commentDto
		                                           ){
	   return new ResponseEntity<CommentDto>(commentService.updateComment(commentId, commentDto),HttpStatus.ACCEPTED);
	   
   }
   @DeleteMapping("{commentId}")
   public ResponseEntity<String> deleteComment(
		                                       @PathVariable int commentId){
	   
	   return new ResponseEntity<String>(commentService.deleteComment(commentId),HttpStatus.OK);
   }
}
