package com.example.StudentDetails.StudentDetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.StudentDetails.StudentDetails.Entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	 List<Student> findByYear(int year);
	    List<Student> findByDepartment(String department);
}
