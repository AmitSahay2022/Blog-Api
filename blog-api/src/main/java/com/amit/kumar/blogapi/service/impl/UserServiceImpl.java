package com.amit.kumar.blogapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.amit.kumar.blogapi.entity.User;
import com.amit.kumar.blogapi.exception.ResourceNotFoundException;
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
	@Override
	public UserDto createUser(UserDto dto) {
		if(userRepository.existsByEmail(dto.getEmail())) {
			throw new UserAllReadyExistException();
		}
		// Convert UserDto to User
		User user = mapper.map(dto, User.class);
		User savedUser = userRepository.save(user);
		
		//Convert savedUser to UserDto type
		
		return mapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto dto, long userId) {
		UserDto userById = getUserById(userId);
		userById.setAbout(dto.getAbout());
		userById.setName(dto.getName());
		userById.setPassword(dto.getPassword());
		//Convert UserDto to user
		User user = mapper.map(userById, User.class);
		//Save User
		User updatedUser = userRepository.save(user);
		return mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(long userId) {
		 User user = userRepository
				                 .findById(userId)
				                 .orElseThrow(
				                		 ()->new ResourceNotFoundException("User Not Found with id: "+userId));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		List<UserDto> userDtos = allUsers.stream().map(u->mapper.map(u, UserDto.class)).toList();
		return userDtos;
	}

	@Override
	public String deleteUser(long userId) {
		UserDto userDto = getUserById(userId);
		//Convert UserDto to user
		User user = mapper.map(userDto, User.class);
		userRepository.delete(user);
		return "User Deleted Successfully";
	}

}
