package com.student.management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.student.management.entity.Student;
import com.student.management.repo.StudentRepo;

public class StudentServiceImplTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentRepo.findAll()).thenReturn(List.of(student));

        List<Student> students = studentService.getAllStudents();
        assertNotNull(students);
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentRepo.findById("1")).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById("1");
        assertNotNull(foundStudent);
        assertEquals("John Doe", foundStudent.getName());
    }

    @Test
    void testInsertStudent() {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentRepo.save(student)).thenReturn(student);

        String result = studentService.insertStudent(student);
        assertEquals("success", result);
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentRepo.existsById("1")).thenReturn(true);
        when(studentRepo.save(student)).thenReturn(student);

        String result = studentService.updateStudent(student);
        assertEquals("success", result);
    }

    @Test
    void testDeleteStudent() {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        doNothing().when(studentRepo).delete(student);

        String result = studentService.deleteStudent(student);
        assertEquals("success", result);
    }

    @Test
    void testDeleteById() {
        when(studentRepo.existsById("1")).thenReturn(true);
        doNothing().when(studentRepo).deleteById("1");

        String result = studentService.deleteById("1");
        assertEquals("success", result);
    }

    @Test
    void testDeleteAll() {
        doNothing().when(studentRepo).deleteAll();

        String result = studentService.deleteAll();
        assertEquals("success", result);
    }
}
