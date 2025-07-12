package com.student.schooldetails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.schooldetails.entity.Student;
import com.student.schooldetails.service.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "John Doe", "Spring High School");
    }

    @Test
    void testFindAllStudents() throws Exception {
        when(studentService.findAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students/all"))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.message").value("Requested student details are given here"))
               // .andExpect(jsonPath("$.status").value("OK"));
    }

    @Test
    void testCreateStudent() throws Exception {
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateStudent() throws Exception {
        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateStudentName() throws Exception {
        doNothing().when(studentService).updateStudentName(1L, "Updated Name");

        mockMvc.perform(put("/students/1/update-name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Updated Name\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Student name updated successfully"));
    }

    @Test
    void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student deleted successfully"));
    }

    @Test
    void testFindBySchoolName() throws Exception {
        when(studentService.findBySchoolName("Spring High School")).thenReturn(student);
        when(studentService.findAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/students/schoolName")
                        .param("schoolName", "Spring High School"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Requested student details are given here according to school name"));
    }
}

