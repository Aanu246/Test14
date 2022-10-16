package com.eazyschool.repository;

import java.util.List;

import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazyschool.model.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Integer> {
	  List<Courses>findByOrderByNameDesc();
	
	
	 List<Courses> findByOrderByName();
}
