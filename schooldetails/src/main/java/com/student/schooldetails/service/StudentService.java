package com.student.schooldetails.service;

import com.student.schooldetails.entity.Student;
import com.student.schooldetails.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        System.out.println("Fetched Students: " + students); // Debugging log
        return students;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student updateStudent(Long id, Student updateStudent) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.findById(id).get().setName(updateStudent.getName());
        }
        return studentRepository.save(updateStudent);
    }
}
