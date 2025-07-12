package com.student.schooldetails.service;

import com.student.schooldetails.entity.Student;
import com.student.schooldetails.exception.StudentDetailsException;
import com.student.schooldetails.repository.RepositoryMockitoTest;
import com.student.schooldetails.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest(classes= {StudentServiceImplTest.class})
public class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student(1L, "John Doe", "Spring High School");
    }

    // Adding test methods 
    @Test
    @Order(1)
    public void testFindAllStudents() {
        List<Student> list = List.of(student);
        when(studentRepository.findAll()).thenReturn(list);

        List<Student> result = studentService.findAllStudents();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }
    @Test
    @Order(2)
    public void testSaveStudent() {
        when(studentRepository.save(student)).thenReturn(student);

        Student saved = studentService.saveStudent(student);

        assertEquals("John Doe", saved.getName());
        verify(studentRepository, times(1)).save(student);
    }
    @Test
    @Order(3)
    public void testUpdateStudent_Success() {
        Student updatedStudent = new Student(1L, "Jane Smith", "Spring High School");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);

        assertEquals("Jane Smith", result.getName());
    }
    @Test
    @Order(4)
    public void testUpdateStudent_NotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(StudentDetailsException.class, () -> {
            studentService.updateStudent(1L, student);
        });
    }
    @Test
    @Order(5)
   
    public void testUpdateStudentName() {
        when(studentRepository.updateStudentNameById("New Name", 1L)).thenReturn(1); // Simulate 1 row updated

        studentService.updateStudentName(1L, "New Name");

        verify(studentRepository, times(1)).updateStudentNameById("New Name", 1L);
    }

    @Test
    @Order(6)
    public void testDeleteStudent_Success() {
        when(studentRepository.existsById(1L)).thenReturn(true);
   

        studentService.deleteStudent(1L);

        verify(studentRepository).deleteById(1L);
    }
    @Test
    @Order(7)
    public void testDeleteStudent_NotFound() {
        when(studentRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            studentService.deleteStudent(1L);
        });

        assertEquals("Student not found with ID: 1", exception.getMessage());
    }
    @Test
    @Order(8)
    public void testFindBySchoolName_MatchFound() {
        Student another = new Student(2L, "Jane", "Another School");
        List<Student> allStudents = List.of(student, another);

        when(studentRepository.findAll()).thenReturn(allStudents);

        Student found = studentService.findBySchoolName("Spring High School");

        assertNotNull(found);
        assertEquals("Spring High School", found.getSchoolName());
    }
    @Test
    @Order(9)
    public void testFindBySchoolName_NotFound() {
        List<Student> allStudents = List.of(student);

        when(studentRepository.findAll()).thenReturn(allStudents);

        Student found = studentService.findBySchoolName("Unknown School");

        assertNull(found);
    }

}

