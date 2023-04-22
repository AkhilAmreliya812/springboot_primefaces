package com.logicbig.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	@Autowired
	StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public void saveStudentrecord(Student student) {
		studentRepository.save(student);
	}

	public StudentRepository getStudentRepository() {
		return studentRepository;
	}

	public Student getStudentById(Long stdId) {
		return studentRepository.findById(stdId).get();
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student serchById(Long stdId) {
		return studentRepository.findById(stdId).get();
	}

	public List<Student> getStudentByStatus(Boolean active, Long stdId) {
		return studentRepository.findByActive(active, stdId);
	}

	public Student getDataByActiveAndId(Boolean active, Long stdId) {
		return studentRepository.findByActiveWithId(active, stdId);
	}

	public Student getDataByActiveOrId(Boolean active, Long stdId) {
		return studentRepository.findByActiveWithIdOr(active, stdId);
	}

//	public String getAllDataByQuery(Boolean active, Long stdId) {
//		return studentRepository.globalQueryForAll(active, stdId);
//	}

	public String getStudent(Boolean active, Long stdId) {

		String a = studentRepository.globalQueryForAll(active, stdId);
		
		System.out.println("Service Class ==> " + a);
		
		return a;
		// return studentRepository.globalQueryForAll(active, stdId);
	}
}