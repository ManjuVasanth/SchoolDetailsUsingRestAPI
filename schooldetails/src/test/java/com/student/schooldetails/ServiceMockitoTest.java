package com.student.schooldetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.student.schooldetails.entity.Student;
import com.student.schooldetails.repository.StudentRepository;
import com.student.schooldetails.service.StudentService;

@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {
	@Mock
	StudentRepository studentRep;
	@InjectMocks
	StudentService studentService;
	public List<Student> myStudents;
	
	/* 
	 *  public List<Student> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println("Fetched Students: " + students); // Debugging log
        return students;
	 */
	@Test
	public void test_findAllStudents() {
		myStudents = new ArrayList<Student>();
		myStudents.add(new Student(1,"Mi","Smith High School"));
		myStudents.add(new Student(2,"Yoku","Ranch High School"));
		myStudents.add(new Student(3,"Ted","SmithGen High School"));
		when(studentRep.findAll()).thenReturn(myStudents);
		assertEquals(3,studentService.findAllStudents().size());
		
	}
}
