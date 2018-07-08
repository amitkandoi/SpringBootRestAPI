package com.amitk.springboot.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.amitk.springboot.rest.dto.StudentDTO;
import com.amitk.springboot.rest.dto.SubjectDTO;
import com.amitk.springboot.rest.model.Student;
import com.amitk.springboot.rest.model.Subject;
import com.amitk.springboot.rest.repository.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {

	public static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);

	@Autowired
	SubjectRepository subjectRepository;

	@Transactional
	public String saveSubject(SubjectDTO subjectDTO) {
		Subject subject = prepareSubject(subjectDTO);
		if (subject.getId() == 0) {
			subjectRepository.save(subject);
			LOGGER.info("Subject saved");
		} else {
			if (subjectRepository.findById(subject.getId()).isPresent()) {
				subjectRepository.save(subject);
				LOGGER.info("Subject Updated");
			} else {
				LOGGER.info("No Subject Exist.");
				return null;
			}
		}
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

	@Override
	public List<SubjectDTO> getAllSubjects() {
		LOGGER.info("Getting All Subject");
		return subjectRepository.findAll().stream().map(obj -> prepareSubjectDTO(obj)).collect(Collectors.toList());
	}

	@Override
	public void deleteSubject(int id) {
		LOGGER.info("Deleting Subject using ID " + id);
		subjectRepository.deleteById(id);
	}

	@Override
	public void deleteAllSubject() {
		LOGGER.info("Deleting All Subject");
		subjectRepository.deleteAll();
	}

	@Override
	public List<SubjectDTO> getAllSubjectsOrdered() {
		LOGGER.info("Getting All subject Ordered by ID");
		return subjectRepository.findAll(new Sort(Sort.Direction.ASC,"id")).stream().map(obj -> prepareSubjectDTO(obj)).collect(Collectors.toList());
	}

}