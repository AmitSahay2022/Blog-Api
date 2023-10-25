package com.amit.kumar.blogapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> invalidData(MethodArgumentNotValidException e){
		Map<String, Object> map=new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach((err)->{
			String fieldName = ((FieldError)err).getField();
			String message = err.getDefaultMessage();
			map.put(fieldName, message);
		});		
		
		map.put("Status", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
	}
}
