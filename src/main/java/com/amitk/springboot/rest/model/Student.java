package com.amitk.springboot.rest.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@EmbeddedId
	StudentPK studentPK;

	@Column(name = "name")
	String name;

	@Column(name = "address")
	String address;

	@Column(name = "email")
	String email;

	public Student(int id, String name, String address, String email, Subject subject) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.studentPK = new StudentPK(id, subject);
	}

	public Student() {
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

	public StudentPK getStudentPK() {
		return studentPK;
	}

	public void setStudentPK(StudentPK studentPK) {
		this.studentPK = studentPK;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", address=" + address + ", email=" + email + ", subject="
				+ studentPK.subject.getId() + "]";
	}

}
