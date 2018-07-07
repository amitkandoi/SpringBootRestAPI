package com.amitk.springboot.rest.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.amitk.springboot.rest.dto.SubjectDTO.SaveAction;
import com.amitk.springboot.rest.dto.SubjectDTO.UpdateAction;

public class StudentDTO {

	@Min(value=1,message="Please provide Student Id.", groups = { UpdateAction.class, SaveAction.class })
	int id;

	@NotBlank(message = "Please Provide student name", groups = { UpdateAction.class, SaveAction.class })
	@Size(min = 3, message = "Name must be Greater than 3 char", groups = { UpdateAction.class, SaveAction.class })
	String name;

	@NotBlank(message = "Please Provide Student Address", groups = { UpdateAction.class, SaveAction.class })
	@Size(min = 10, message = "Please Provide Valid Address", groups = { UpdateAction.class, SaveAction.class })
	String address;

	@NotBlank(message = "Please provide Email Address", groups = { UpdateAction.class, SaveAction.class })
	@Email(message = "Please Provide Valid Email Address", groups = { UpdateAction.class, SaveAction.class })
	String email;

	public StudentDTO() {
	}

	public StudentDTO(int id, String name, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + "]";
	}

}
