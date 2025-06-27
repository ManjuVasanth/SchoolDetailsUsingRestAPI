package com.student.schooldetails.service;

import com.student.schooldetails.entity.Student;
import com.student.schooldetails.exception.StudentDetailsException;
import com.student.schooldetails.repository.StudentRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
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


    /* public Student updateStudent(Long id, Student updateStudent) {
        if (studentRepository.findById(id).isPresent()) {
            studentRepository.findById(id).get().setName(updateStudent.getName());
        }
        return studentRepository.save(updateStudent);
    }*/
    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
            .map(existingStudent -> {
                existingStudent.setName(updatedStudent.getName());
                             return studentRepository.save(existingStudent);
            })
            .orElseThrow(() -> new StudentDetailsException("Student with ID " + id + " not found"));
    }
    @Transactional
    public void updateStudentName(Long id, String name) {
        studentRepository.updateStudentNameById(name, id);
    }
    public void deleteStudent(Long id) {
    	 if (!studentRepository.existsById(id)) {
    	        throw new RuntimeException("Student not found with ID: " + id);
    	    }
    	studentRepository.deleteById(id);
    }

	@Override
	public Student findBySchoolName(String schoolName) {
		List<Student> students = studentRepository.findAll();
		Student student = null;
		for(Student per :students) {
			if(per.getSchoolName().equalsIgnoreCase(schoolName)) {
				student = per;
			}
			
		}
		return student;
		
	}
}

