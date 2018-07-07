package com.amitk.springboot.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class StudentPK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="id")
	int id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="subject_id")
	Subject subject;

	public StudentPK(int id,Subject subject) {
		this.id=id;
		this.subject=subject;
	}

	public StudentPK() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((subject == null) ? 0 : ((Integer)(subject.getId())).hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentPK other = (StudentPK) obj;
		if (id != other.id)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!(subject.getId()==other.subject.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentPK [id=" + id + ", subject_ID=" + subject.getId() + "]";
	}
	
}
