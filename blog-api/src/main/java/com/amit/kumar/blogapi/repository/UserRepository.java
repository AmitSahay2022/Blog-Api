package com.amit.kumar.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amit.kumar.blogapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   boolean existsByEmail(String email);
}
