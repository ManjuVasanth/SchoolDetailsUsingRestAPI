package com.student.schooldetails.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	//Handle specific exception  --> SchoolDetailsException
	
	@ExceptionHandler(StudentDetailsException.class)
	public ResponseEntity<ErrorDetails> handleSchoolException(StudentDetailsException exception, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				exception.getMessage(), 
				webRequest.getDescription(false), 
				"STUDENT_NOT_FOUND");
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
	// Handle Generic Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGenericExcception(Exception exception,WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				exception.getMessage(), 
				webRequest.getDescription(false), 
				"INTERNAL_SERVER_ERROR");
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
