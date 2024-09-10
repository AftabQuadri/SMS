package com.student.management.repo;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.management.entity.Student;

public interface StudentRepo extends MongoRepository<Student, Serializable> {

}
