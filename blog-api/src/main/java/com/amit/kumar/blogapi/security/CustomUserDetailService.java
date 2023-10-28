package com.amit.kumar.blogapi.security;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amit.kumar.blogapi.entity.User;
import com.amit.kumar.blogapi.exception.ResourceNotFoundException;
import com.amit.kumar.blogapi.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
	private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	// TODO Auto-generated method stub
    	User user = userRepository
    			         .findByEmail(username)
    			         .orElseThrow((()->new ResourceNotFoundException("User Not Found")));
    	return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
