package com.student.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.management.entity.User;
import com.student.management.service.UserService;
import com.student.management.util.JwtUtil;

@RestController
@RequestMapping("/users/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userFromDB = userService.getUserByName(user.getUsername());
		if (userFromDB != null) {
			return ResponseEntity.badRequest()
					.body("User exists with the same username. Please provide another username.");
		}

		try {
			userService.saveUserInDB(user);
			System.out.println("User registered: " + user);
			return ResponseEntity.ok("User registered successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("An error occurred while registering the user.");
		}
	}

	@PostMapping("login")
	public ResponseEntity<String> logIn(@RequestBody User user) {
		try {
			System.out.println(user);
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			if (authentication.isAuthenticated()) {
				return ResponseEntity.ok(jwtUtil.generateToken(user.getUsername()));
			} else {

				return ResponseEntity.badRequest().body("Invalid user request!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Invalid user request!");
		}

	}
}