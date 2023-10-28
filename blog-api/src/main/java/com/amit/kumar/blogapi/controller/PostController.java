package com.amit.kumar.blogapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amit.kumar.blogapi.payloads.PostDto;
import com.amit.kumar.blogapi.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {
	private PostService postService;

	@PostMapping("{categoryId}")
	public ResponseEntity<PostDto> savePost(@PathVariable int categoryId,
			@RequestBody PostDto postDto) {
		return new ResponseEntity<PostDto>(postService.savePost(categoryId, postDto), HttpStatus.CREATED);

	}
	
	@PutMapping("{postId}")
	public ResponseEntity<PostDto> updatePost(			
			@PathVariable int postId,
			@RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.updatePost(postId, postDto),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{postId}")
	public ResponseEntity<String> deletePost(@PathVariable int postId){
		return new ResponseEntity<String>(postService.deletePost(postId),HttpStatus.OK);
	}
	
	@GetMapping("{userId}/{postId}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable long userId,@PathVariable int postId){
		return new ResponseEntity<PostDto>(postService.getPostByPostId(userId, postId),HttpStatus.OK);
	}
	@GetMapping("/post-by-user/{userId}")
	public ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable long userId){
		return new ResponseEntity<List<PostDto>>(postService.getAllPostByUserId(userId),HttpStatus.OK);
	}
	@GetMapping("/post-by-category/{catId}")
	public ResponseEntity<List<PostDto>> getAllPostByCategoryId(@PathVariable int catId){
		return new ResponseEntity<List<PostDto>>(postService.getAllPostByCategoryId(catId),HttpStatus.OK);
	}
	@GetMapping("/post-by-title")
	public ResponseEntity<List<PostDto>> getAllPostByCategoryTitle(@RequestParam String title){
		return new ResponseEntity<List<PostDto>>(postService.getAllPostByCategoryTitle(title),HttpStatus.OK);
	}
	
}
