package com.student.schooldetails.service;

import java.util.List;

import com.student.schooldetails.entity.Student;

public interface StudentService {
	 public List<Student> findAllStudents();
	 public Student saveStudent(Student student); 
	 public Student updateStudent(Long id, Student updatedStudent);
	 public void updateStudentName(Long id, String name);
	 public void deleteStudent(Long id);
	 Student findBySchoolName(String schoolName);
	 
}
