package com.student.schooldetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.student.schooldetails.service.StudentServiceImpl;

@SpringBootApplication
public class SchooldetailsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SchooldetailsApplication.class, args);
	}
	@Autowired
	private StudentServiceImpl studentService;

	public void setStudentService(StudentServiceImpl studentService) {
		this.studentService = studentService;
	}
	
	public void run(String...args) throws Exception{
		studentService.updateStudentName(8L, "Jo Doe");
        System.out.println("Student name updated successfully.");
	}
	

}
