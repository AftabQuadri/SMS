package com.student.management.service;

import java.util.List;

import com.student.management.entity.Student;

public interface StudentService {
    public List<Student> getAllStudents();
    
    public Student getStudentById(String id);
    
    public String insertStudent(Student student);
    
    public String updateStudent(Student student);
    
    public String deleteStudent(Student student);
    
    public String deleteById(String id);
    
    public String deleteAll();

}
