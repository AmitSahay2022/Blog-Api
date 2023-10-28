package com.amit.kumar.blogapi.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amit.kumar.blogapi.entity.User;
import com.amit.kumar.blogapi.exception.UserAllReadyExistException;
import com.amit.kumar.blogapi.payloads.UserDto;
import com.amit.kumar.blogapi.repository.UserRepository;
import com.amit.kumar.blogapi.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper mapper;
    private PasswordEncoder passwordEncoder;
	@Override
	public UserDto createUser(UserDto dto) {
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new UserAllReadyExistException();
		}
		// Convert UserDto to User
		User user = mapper.map(dto, User.class);
		// Encrypt Password
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		User savedUser = userRepository.save(user);
		
		//Convert savedUser to UserDto type
		
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto dto) {
		UserDto userById = getUserById();
		userById.setAbout(dto.getAbout());
		userById.setName(dto.getName());
		userById.setPassword(dto.getPassword());
		//Convert UserDto to user
		User user = mapper.map(userById, User.class);
		// Encrypt Password
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		//Save User
		User updatedUser = userRepository.save(user);
		return mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById() {
		 User user = getLoggedInUser();
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		List<UserDto> userDtos = allUsers.stream().map(u->mapper.map(u, UserDto.class)).toList();
		return userDtos;
	}

	@Override
	public String deleteUser() {
		UserDto userDto = getUserById();
		//Convert UserDto to user
		User user = mapper.map(userDto, User.class);
		userRepository.delete(user);
		return "User Deleted Successfully";
	}

	//Extract Login User from SecurityContextHolder
	
	private User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		return userRepository.findByEmail(userEmail).get();
	}
}
