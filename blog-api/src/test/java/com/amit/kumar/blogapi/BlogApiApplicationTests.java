package com.amit.kumar.blogapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.amit.kumar.blogapi.repository.UserRepository;

@SpringBootTest
class BlogApiApplicationTests {
    @Autowired
	private UserRepository userRepository;
	@Test
	void contextLoads() {
	}
	
	@Test
	void repoTest() {
		System.out.println(userRepository.getClass().getName());
	}

}
