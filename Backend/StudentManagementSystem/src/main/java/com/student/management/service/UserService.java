package com.student.management.service;

import com.student.management.entity.User;

public interface UserService {

	public User getUserByName(String username);

	public User saveUserInDB(User user);

}
