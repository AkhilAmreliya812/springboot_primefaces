package com.logicbig.example;

import java.sql.Connection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	String a = "";
	
	@Query("select s from Student s where s.active = ?1 or s.stdId = ?2")
	List<Student> findByActive(Boolean active, Long  stdId);
	
	@Query("select s from Student s where s.active = ?1 and s.stdId = ?2")
	Student findByActiveWithId(Boolean active, Long stdId);
	
	@Query("select s from Student s where s.active = ?1 or s.stdId = ?2")
	Student findByActiveWithIdOr(Boolean active, Long stdId);	
	
	public default String globalQueryForAll(Boolean active, Long stdId) {

		String sql = "select * from std_basic_info";
		if (active == true) {
			sql += " where is_active = " + active;
		} else if (active == false) {
			sql += " where is_active = " + active;
		} else if (active == null) {
			sql += " where is_active = " + active;
		}
		if (stdId != null) {
			sql += " and std_Id = " + stdId;
		} else {
			sql += " or std_Id = " + stdId;
		}
		 
		//Query query = em.createNativeQuery(sql,Student.class);
		//List<Student> studentList = query
		
		return sql + ";";
	}

}
