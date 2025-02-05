package com.student.management.repo;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.management.entity.User;

public interface UserRepo extends MongoRepository<User, Serializable> {
public User findByusername(String username);
}
