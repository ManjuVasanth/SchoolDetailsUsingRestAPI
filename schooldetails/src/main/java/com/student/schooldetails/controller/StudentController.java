package com.student.schooldetails.controller;

import com.student.schooldetails.entity.Student;
import com.student.schooldetails.response.ResponseHandler;
import com.student.schooldetails.service.StudentServiceImpl;

import jakarta.servlet.http.HttpServletRequest;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@ResponseBody

public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;
    @GetMapping("/all")
    public ResponseEntity<Object> findAllStudents(){
    	 return ResponseHandler.responseBuilder("Requested student details are given here",HttpStatus.OK, studentService.findAllStudents());
       
    }
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student  student){
        return studentService.updateStudent(id,student);
    }
    
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
    	return (CsrfToken) request.getAttribute("_csrf");
    }
    
    @PutMapping("/{id}/update-name")
    public ResponseEntity<Object> updateStudentName(@PathVariable Long id, @RequestBody String name) {
        studentService.updateStudentName(id, name);
        return ResponseHandler.responseBuilder("Student name updated successfully", HttpStatus.OK, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
    @GetMapping("/schoolName")
    public ResponseEntity<Object> findBySchoolName(@PathVariable String schoolName){
    	studentService.findBySchoolName(schoolName);
    	 return ResponseHandler.responseBuilder("Requested student details are given here according to school name",HttpStatus.OK, studentService.findAllStudents());
       
    }

}
