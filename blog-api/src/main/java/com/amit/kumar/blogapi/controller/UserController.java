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
import org.springframework.web.bind.annotation.RestController;

import com.amit.kumar.blogapi.payloads.UserDto;
import com.amit.kumar.blogapi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    
    //Create User API
    
    @PostMapping
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
    	return new ResponseEntity<>(userService.createUser(userDto),HttpStatus.CREATED);
    }
    
    //Get All Users
    
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
    	return new ResponseEntity<List<UserDto>>(userService.getAllUsers(),HttpStatus.OK);
    }
    
    //Get User By Id
    
    @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long userId){
    	return new ResponseEntity<UserDto>(userService.getUserById(userId),HttpStatus.OK);
    }
    
    //Update User API
    
    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable long userId){
    	return new ResponseEntity<UserDto>(userService.updateUser(userDto, userId),HttpStatus.ACCEPTED);
    }
    
    //Delete User By Id
    
    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId){
    	return new ResponseEntity<String>(userService.deleteUser(userId),HttpStatus.OK);
    }
}
