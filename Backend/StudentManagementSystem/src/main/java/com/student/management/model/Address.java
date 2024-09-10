package com.student.management.model;

import lombok.Data;

@Data
public class Address {
	private String street;
	private String city;
	private String state;
	private String country;
	// not including pin as it is already present in Student Entity/Document
}
