package com.greatlearning.sra.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.sra.model.Student;
import com.greatlearning.sra.repository.StudentRepository;
import com.greatlearning.sra.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repository;

	@Override
	public Set<Student> getAllStudents() {

		return Set.copyOf(this.repository.findAll());
	}

	@Override
	public Student addStudent(Student student) {

		return this.repository.save(student);
	}

	@Override
	public Student findStudentById(int id) {

		return this.repository.findById(id)
				.orElseThrow();
	}

	@Override
	public Student updateStudent(int id, Student updateStudent) {
		Student student = this.findStudentById(id);
		student.setFirstName(updateStudent.getFirstName());
		student.setLastName(updateStudent.getLastName());
		student.setCourse(updateStudent.getCourse());
		student.setCountry(updateStudent.getCountry());

		return this.repository.save(student);
	}

	@Override
	public void deleteStudentById(int id) {

		this.repository.deleteById(id);

	}

}
