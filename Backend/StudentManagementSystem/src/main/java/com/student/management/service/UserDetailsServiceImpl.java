package com.student.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.student.management.entity.User;
import com.student.management.repo.UserRepo;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user=userRepo.findByusername(username);
		 if(user!=null) {
			 UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
			 .username(user.getUsername())
			 .password(user.getPassword())
			 .build();
			 return userDetails;
		 }
		throw new UsernameNotFoundException("User Not Found with Username "+username);
	}

}
