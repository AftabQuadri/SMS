package com.student.management.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.student.management.model.Address;
import com.student.management.model.ContactDetails;

import lombok.Data;

@Document(collection = "students")
@Data
public class Student {
	@Id
	private String id;
	private String name;
	private ContactDetails contactDetails;
	private Address address;
	private String pincode;
}
