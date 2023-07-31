package com.greatlearning.sra.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message = "First name must not be empty")
	private String firstName;
	
	@NotEmpty(message = "Last name must not be empty")
	private String lastName;
	
	@NotEmpty(message = "Course must not be empty")
	private String course;
	
	@NotEmpty(message = "Country must not be empty")
	private String country;
	
	public Student(String fName, String lName, String course, String country) {
		
		this.firstName = fName;
		this.lastName = lName;
		this.course = course;
		this.country = country;
	}

}
