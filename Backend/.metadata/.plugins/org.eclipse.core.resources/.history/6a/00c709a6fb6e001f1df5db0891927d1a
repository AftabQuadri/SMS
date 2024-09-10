package com.student.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.management.entity.Student;
import com.student.management.service.StudentService;

@RestController
@RequestMapping("/")
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("add-student")
	public ResponseEntity<String> insertSingleStudent(@RequestBody Student student) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.out.println(authentication);
			if (authentication != null) {
				String message = service.insertStudent(student);
				if (message.equalsIgnoreCase("success")) {
					return ResponseEntity.status(HttpStatus.CREATED).body("Student added successfully");
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.badRequest().body("There was an error creating a new student");
	}

	@GetMapping("get-all")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> allStudentsFromDB = service.getAllStudents();
		if (allStudentsFromDB != null && !allStudentsFromDB.isEmpty()) {
			return ResponseEntity.ok(allStudentsFromDB);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable String id) {
		Student studentFromDB = service.getStudentById(id);
		if (studentFromDB != null) {
			return new ResponseEntity<>(studentFromDB, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PutMapping("update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			String message = service.updateStudent(student);
			if (message.equalsIgnoreCase("success")) {
				return new ResponseEntity<>("Student updated successfully", HttpStatus.OK);
			}
				
		}
		return ResponseEntity.badRequest().body("There was an error updating the student");
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable String id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			String message = service.deleteById(id);
			if (message.equalsIgnoreCase("success")) {
				return ResponseEntity.ok("Student deleted successfully");
			}
				
		}
		return ResponseEntity.badRequest().body("There was an error deleting the student");
	}

	@DeleteMapping("delete-all")
	public ResponseEntity<String> deleteAllStudents() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication!=null) {
	    	String message = service.deleteAll();
			if (message.equalsIgnoreCase("success")) {
				return ResponseEntity.ok("All students deleted successfully");
			}
				
	    }
		return ResponseEntity.badRequest().body("There was an error deleting all students");
	}

//	@PostMapping("gettoken")
//	public String generateToken(@RequestBody User user) {
//		try {
//			System.out.println(user);
//			Authentication authentication = authenticationManager
//					.authenticate(new UsernamePasswordAuthenticationToken(
//							user.getUsername(), user.getPassword()
//							));
//			System.out.println(user);
//			if (authentication.isAuthenticated()) {
//				return jwtUtil.generateToken(user.getUsername());
//			} else {
//				throw new UsernameNotFoundException("Invalid user request!");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new UsernameNotFoundException("Invalid user request!");
//		}
//	}

}
