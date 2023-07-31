package com.greatlearning.sra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greatlearning.sra.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

}
