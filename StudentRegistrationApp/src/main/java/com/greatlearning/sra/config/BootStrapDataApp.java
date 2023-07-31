package com.greatlearning.sra.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.sra.model.Role;
import com.greatlearning.sra.model.Student;
import com.greatlearning.sra.model.User;
import com.greatlearning.sra.repository.StudentRepository;
import com.greatlearning.sra.repository.UserRepository;

@Configuration
public class BootStrapDataApp {
	
	@Autowired
	private StudentRepository repository;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void loadUser(ApplicationReadyEvent event) {
		
		Role user = new Role("ROLE_USER");
		Role admin = new Role("ROLE_ADMIN");
		
		User student = new User("student",this.passwordEncoder.encode("zxcvb"));
		User teacher = new User("teacher",this.passwordEncoder.encode("zxcvb"));
		
		student.addrole(user);
		teacher.addrole(admin);
		
		this.userRepo.save(student);
		this.userRepo.save(teacher);
		
	}
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void loadStudents(ApplicationReadyEvent event) {
		
		Student s1 = new Student("Victoria","Lovegood","B.Com","USA");
		Student s2 = new Student("Kim", "Chuu","Arts","SouthKorea");
		Student s3 = new Student("Punnya","Pillai","Psychology","India");
		Student s4 = new Student("Daniel","Denson","B.Arch","New Zealand");
		Student s5 = new Student("Emma","Watson","B.Arch","England");
		Student s6 = new Student("Jeon","Jungkook","Arts","SouthKorea");
		Student s7 = new Student("Kumari","Durga","B.Tech/BioTech","India");
		
		this.repository.save(s1);
		this.repository.save(s2);
		this.repository.save(s3);
		this.repository.save(s4);
		this.repository.save(s5);
		this.repository.save(s6);
		this.repository.save(s7);
		
	}

}
