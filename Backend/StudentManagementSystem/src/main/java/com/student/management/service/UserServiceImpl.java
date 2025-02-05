package com.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.management.entity.User;
import com.student.management.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepo userRepo;
	public User getUserByName(String username) {
		User user=null;
		try {
			user=userRepo.findByusername(username);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public User saveUserInDB(User user) {
		User savedUser=null;
		try {
			  savedUser = userRepo.save(user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return savedUser;
	}
}
