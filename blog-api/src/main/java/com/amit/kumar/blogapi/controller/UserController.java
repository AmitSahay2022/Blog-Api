package com.amit.kumar.blogapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amit.kumar.blogapi.payloads.LoginData;
import com.amit.kumar.blogapi.payloads.UserDto;
import com.amit.kumar.blogapi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    //Create User API
    
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
    	return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
    }
    
    
    
    //Get User Details
    
    @GetMapping()
    public ResponseEntity<UserDto> getUserDetails(){
    	return new ResponseEntity<UserDto>(userService.getUserById(),HttpStatus.OK);
    }
    
    //Update User API
    
    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto){
    	return new ResponseEntity<UserDto>(userService.updateUser(userDto),HttpStatus.ACCEPTED);
    }
    
    //Delete User
    
    @DeleteMapping()
    public ResponseEntity<String> deleteUser(){
    	return new ResponseEntity<String>(userService.deleteUser(),HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginData data){
    	Authentication authenticate = authenticationManager
    			                          .authenticate(
    			                        		  new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
    	SecurityContextHolder.getContext().setAuthentication(authenticate);
    	
    	return new ResponseEntity<String>("Login Successful",HttpStatus.OK);
    }
}
