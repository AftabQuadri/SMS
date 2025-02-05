package com.student.management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.management.entity.Student;
import com.student.management.repo.StudentRepo;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(String id) {
        Optional<Student> studentById = studentRepo.findById(id);
        return studentById.orElse(null);
    }

    public String insertStudent(Student student) {
        Student result = studentRepo.save(student);
        return result != null ? "success" : "error";
    }

    public String updateStudent(Student student) {
        if (studentRepo.existsById(student.getId())) {
            Student result = studentRepo.save(student);
            return result != null ? "success" : "error";
        }
        return "error: student not found";
    }

    public String deleteStudent(Student student) {
        try {
            studentRepo.delete(student);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error: unable to delete student";
        }
    }

    public String deleteById(String id) {
        if (studentRepo.existsById(id)) {
            try {
                studentRepo.deleteById(id);
                return "success";
            } catch (Exception e) {
                e.printStackTrace();
                return "error: unable to delete student";
            }
        }
        return "error: student not found";
    }

    @Transactional
    public String deleteAll() {
        try {
            studentRepo.deleteAll();
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error: unable to delete all students";
        }
    }
}
