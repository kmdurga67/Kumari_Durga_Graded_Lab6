package com.greatlearning.sra.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.sra.model.Student;
import com.greatlearning.sra.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService service;

	@GetMapping
	public Set<Student> getAllStudents() {
		return this.service.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable("id") int id) {

		return this.service.findStudentById(id);
	}

	@PostMapping
	public Student addStudent(@Valid @RequestBody Student student) {

		return this.service.addStudent(student);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable("id") int id, @Valid @RequestBody Student updatedStudent) {

		return this.service.updateStudent(id, updatedStudent);
	}

	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable("id") int id) {

		this.service.deleteStudentById(id);
		
		return "Student with id " + id + " deleted.";
	}
}
