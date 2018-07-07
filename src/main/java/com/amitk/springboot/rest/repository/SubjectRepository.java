package com.amitk.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amitk.springboot.rest.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer>{
	
	@Query(value="select id from Subject")
	public List<String> getSubjectIds();
	
	List<Subject> findByName(String name);
}
