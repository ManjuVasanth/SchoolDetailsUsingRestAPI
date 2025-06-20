package com.student.schooldetails.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)

	private String name;
	@Column(nullable = false)

	private String schoolName;

	public Student() {
	}

	public Student(long id, String name, String schoolName) {
		this.id = id;
		this.name = name;
		this.schoolName = schoolName;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Override
	public String toString() {
		return "Student{" + "id=" + id + ", name='" + name + '\'' + ", schoolName='" + schoolName + '\'' + '}';
	}
}
