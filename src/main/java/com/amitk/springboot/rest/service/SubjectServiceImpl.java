package com.amitk.springboot.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amitk.springboot.rest.dto.StudentDTO;
import com.amitk.springboot.rest.dto.SubjectDTO;
import com.amitk.springboot.rest.model.Student;
import com.amitk.springboot.rest.model.Subject;
import com.amitk.springboot.rest.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectRepository subjectRepository;

	
	public String saveSubject(SubjectDTO subjectDTO) {
		Subject subject = prepareSubject(subjectDTO);
		subjectRepository.save(subject);
		return String.valueOf(subject.getId());
	}

	public SubjectDTO getSubject(int id) {
		Optional<Subject> subject = subjectRepository.findById(id);
		SubjectDTO subjectDTO;
		if (subject.isPresent()) {
			subjectDTO = prepareSubjectDTO(subject.get());
		} else {
			return null;
		}
		return subjectDTO;
	}

	public List<String> getSubjectIds() {
		return subjectRepository.getSubjectIds();
	}

	public List<SubjectDTO> getSubjectByName(String name) {
		return subjectRepository.findByName(name).stream().map(obj -> prepareSubjectDTO(obj))
				.collect(Collectors.toList());
	}
	public SubjectDTO prepareSubjectDTO(Subject subject) {
		SubjectDTO subjectDTO = new SubjectDTO();
		subjectDTO.setId(String.valueOf(subject.getId()));
		subjectDTO.setName(subject.getName());
		subjectDTO.setType(subject.getType());
		subjectDTO.setStudent(subject.getStudent().stream()
				.map(obj -> new StudentDTO(obj.getStudentPK().getId(), obj.getName(), obj.getEmail(), obj.getAddress()))
				.collect(Collectors.toList()));
		return subjectDTO;
	}

	public Subject prepareSubject(SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		if (subjectDTO.getId() != null) {
			subject.setId(Integer.parseInt(subjectDTO.getId()));
		}
		subject.setName(subjectDTO.getName());
		subject.setType(subjectDTO.getType());
		subject.setStudent(subjectDTO.getStudent().stream()
				.map(obj -> new Student(obj.getId(), obj.getName(), obj.getAddress(), obj.getEmail(), subject))
				.collect(Collectors.toList()));
		return subject;
	}
	
}