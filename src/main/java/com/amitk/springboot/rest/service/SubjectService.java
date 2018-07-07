package com.amitk.springboot.rest.service;

import java.util.List;

import com.amitk.springboot.rest.dto.SubjectDTO;

public interface SubjectService {

	public String saveSubject(SubjectDTO subjectDTO);
	public SubjectDTO getSubject(int id);
	public List<String> getSubjectIds();
	List<SubjectDTO> getSubjectByName(String name);
}
