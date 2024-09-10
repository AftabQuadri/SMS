package com.student.management.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.management.controller.StudentController;
import com.student.management.entity.Student;
import com.student.management.service.StudentService;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void testInsertSingleStudent() throws Exception {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentService.insertStudent(any(Student.class))).thenReturn("success");

        mockMvc.perform(post("/add-student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Student added successfully"));
    }

    @Test
    void testGetAllStudents() throws Exception {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/get-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentService.getStudentById("1")).thenReturn(student);

        mockMvc.perform(get("/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        Student student = new Student();
        student.setId("1");
        student.setName("John Doe");

        when(studentService.updateStudent(any(Student.class))).thenReturn("success");

        mockMvc.perform(put("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("Student updated successfully"));
    }

    @Test
    void testDeleteStudentById() throws Exception {
        when(studentService.deleteById("1")).thenReturn("success");

        mockMvc.perform(delete("/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully"));
    }

    @Test
    void testDeleteAllStudents() throws Exception {
        when(studentService.deleteAll()).thenReturn("success");

        mockMvc.perform(delete("/delete-all"))
                .andExpect(status().isOk())
                .andExpect(content().string("All students deleted successfully"));
    }
}
