package com.amitk.springboot.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.amitk.springboot.rest.dto.SubjectDTO;
import com.amitk.springboot.rest.dto.SubjectDTO.SaveAction;
import com.amitk.springboot.rest.dto.SubjectDTO.UpdateAction;
import com.amitk.springboot.rest.service.SubjectService;
import com.amitk.springboot.rest.util.error.CustomException;

/**
 * @author amitk
 *
 */
@RestController
public class JPAandValidatorController {

	public static final Logger LOGGER = LoggerFactory.getLogger(JPAandValidatorController.class);

	@Autowired
	SubjectService subjectService;

	/**
	 * @return
	 */
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<?> hello() {
		LOGGER.info("inside hello method");
		return new ResponseEntity<String>("Hello", HttpStatus.OK);
	}

	/**
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@Validated({ SaveAction.class }) @RequestBody SubjectDTO subject) {
		LOGGER.info("Saving the Subject");
		String id = subjectService.saveSubject(subject);
		return new ResponseEntity<String>("Saved id:= " + id, HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSubject(@Validated({ UpdateAction.class }) @RequestBody SubjectDTO subject) {
		LOGGER.info("Updating the Subject");
		String id = subjectService.saveSubject(subject);
		if(id==null) {
			throw new CustomException("Please Provide Valid Subject Id");
		}
		return new ResponseEntity<String>("Saved id:= " + id, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getsubject/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getSubject(@PathVariable int id) {
		SubjectDTO subjectDTO = subjectService.getSubject(id);
		if (subjectDTO == null) {
			throw new CustomException("No Subject Found");
		}
		return new ResponseEntity<SubjectDTO>(subjectDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/getsubjectids", method = RequestMethod.GET)
	public ResponseEntity<?> getSubjectids() {
		List<String> listOfData = subjectService.getSubjectIds();
		if (listOfData == null) {
			throw new CustomException("No Subject Exist");
		}
		return new ResponseEntity<List<String>>(listOfData, HttpStatus.OK);
	}

	@RequestMapping(value = "/getsubjectbyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<?> getSubjectidByName(@PathVariable String name) {
		List<SubjectDTO> listOfData = subjectService.getSubjectByName(name);
		if (listOfData == null || listOfData.size()==0) {
			return new ResponseEntity<String>("No Subject Exist", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SubjectDTO>>(listOfData, HttpStatus.OK);
	}

	@RequestMapping(value = "/getallsubjects", method = RequestMethod.GET)
	public ResponseEntity<?> getAllSubjects() {
		List<SubjectDTO> listOfData = subjectService.getAllSubjects();
		if (listOfData == null) {
			throw new CustomException("No Subject Exist");
		}
		return new ResponseEntity<List<SubjectDTO>>(listOfData, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getallsubjectsodered", method = RequestMethod.GET)
	public ResponseEntity<?> getAllSubjectsOrderById() {
		List<SubjectDTO> listOfData = subjectService.getAllSubjectsOrdered();
		if (listOfData == null) {
			throw new CustomException("No Subject Exist");
		}
		return new ResponseEntity<List<SubjectDTO>>(listOfData, HttpStatus.OK);
	}

	@RequestMapping(value = "/deletesubject/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSubjectById(@PathVariable int id) {
		try {
			subjectService.deleteSubject(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new CustomException("No Subject Exist with ID : " + id);
		}
		return new ResponseEntity<String>("Subject Deleted.", HttpStatus.OK);
	}

	@RequestMapping(value = "/deletesubject", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteSubject() {
		try {
			subjectService.deleteAllSubject();
		} catch (EmptyResultDataAccessException ex) {
			throw new CustomException("No Subject Exist");
		}
		return new ResponseEntity<String>("All Subject Deleted", HttpStatus.OK);
	}
}
