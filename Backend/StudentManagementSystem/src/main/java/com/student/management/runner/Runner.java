package com.student.management.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.student.management.entity.Student;
import com.student.management.entity.User;
import com.student.management.model.Address;
import com.student.management.model.ContactDetails;
import com.student.management.service.StudentService;
import com.student.management.service.UserService;
@Component
public class Runner implements ApplicationRunner{

	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		User admin = new User();
		admin.setUsername("Aftab");
		admin.setPassword(passwordEncoder.encode("admin"));
		if (userService.getUserByName(admin.getUsername()) == null) {
			userService.saveUserInDB(admin);
		}	
		
		
		for(int i=0;i<10;i++) {
			//this is just demo data so that our UI table does not look empty
			 Student student = new Student();
	            student.setId("ID" + i); 
	            student.setName("Student " + i);
	            student.setPincode("1000" + i);

	            ContactDetails contactDetails = new ContactDetails();
	            contactDetails.setPhone("123-456-789" + i);
	            contactDetails.setEmail("student" + i + "@example.com");
	            student.setContactDetails(contactDetails);

	            Address address = new Address();
	            address.setStreet("Street " + i);
	            address.setCity("City " + i);
	            address.setState("State " + i);
	            address.setCountry("Country " + i);
	            student.setAddress(address);

	            studentService.insertStudent(student);
		}
	}

	
	
}
