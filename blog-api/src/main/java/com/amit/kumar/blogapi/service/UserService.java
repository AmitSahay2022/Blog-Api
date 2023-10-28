package com.amit.kumar.blogapi.service;

import java.util.List;

import com.amit.kumar.blogapi.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto dto);

	UserDto updateUser(UserDto dto);

	UserDto getUserById();

	List<UserDto> getAllUsers();

	String deleteUser();
}
