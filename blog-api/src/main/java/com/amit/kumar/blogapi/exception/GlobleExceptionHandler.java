package com.amit.kumar.blogapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> userNotFound(ResourceNotFoundException e) {
		Map<String, Object> map = new HashMap<>();
		map.put("message", e.getLocalizedMessage());
		map.put("status code", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(UserAllReadyExistException.class)
	public ResponseEntity<Map<String, Object>> userExist(UserAllReadyExistException e){
		Map<String, Object> map=new HashMap<>();
		map.put("message", e.getLocalizedMessage());
		map.put("status", HttpStatus.CONFLICT);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CONFLICT);
	}
}
