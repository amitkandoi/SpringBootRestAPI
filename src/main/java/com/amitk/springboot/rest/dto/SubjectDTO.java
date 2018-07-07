package com.amitk.springboot.rest.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.amitk.springboot.rest.validator.Name;

public class SubjectDTO {

	public static final Logger LOGGER = LoggerFactory.getLogger(SubjectDTO.class);

	public interface SaveAction { // Validation Group, will be used for saving the Subject
	};

	public interface UpdateAction { // Validation Group, will be used for Updating the Subject
	};

	@Null(message = "ID will be auto generated, to update the Subject please use /save with PUT Request API.", groups = {
			SaveAction.class })
	String id;

	@NotBlank(message = "Please Provide Subject Name", groups = { UpdateAction.class, SaveAction.class })
	@Size(min = 2, message = "Subject Name must be Greater than 2 char", groups = { UpdateAction.class,
			SaveAction.class })
	@Name(groups = { UpdateAction.class, SaveAction.class })
	String name;

	@NotBlank(message = "Please provide Subject Type", groups = { UpdateAction.class, SaveAction.class })
	@Size(min = 2, message = "Subject Type must be Greater than 2 char", groups = { UpdateAction.class,
			SaveAction.class })
	String type;

	@Valid
	List<StudentDTO> student;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<StudentDTO> getStudent() {
		return student;
	}

	public void setStudent(List<StudentDTO> student) {
		LOGGER.info("setting the student");
		this.student = student;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", type=" + type + ", student=" + student + "]";
	}

}
