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
  
   @PostMapping("{userId}/{postId}")
   public ResponseEntity<CommentDto> saveComment(@PathVariable long userId,
		                                         @PathVariable int postId,
		                                         @RequestBody CommentDto commentDto){
	   return new ResponseEntity<CommentDto>(commentService.createComment(userId, postId, commentDto),HttpStatus.CREATED);
   }
   
   @PutMapping("{userId}/{commentId}")
   public ResponseEntity<CommentDto> updateComment(@PathVariable long userId,
		                                           @PathVariable int commentId,
		                                           @RequestBody CommentDto commentDto
		                                           ){
	   return new ResponseEntity<CommentDto>(commentService.updateComment(userId, commentId, commentDto),HttpStatus.ACCEPTED);
	   
   }
   @DeleteMapping("{userId}/{commentId}")
   public ResponseEntity<String> deleteComment(@PathVariable long userId,
		                                       @PathVariable int commentId){
	   
	   return new ResponseEntity<String>(commentService.deleteComment(userId, commentId),HttpStatus.OK);
   }
}
