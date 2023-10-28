package com.amit.kumar.blogapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amit.kumar.blogapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   boolean existsByEmail(String email);
   
   //This below method will be used by UserDetailsService to get user from DB
   Optional<User> findByEmail(String email);
}
