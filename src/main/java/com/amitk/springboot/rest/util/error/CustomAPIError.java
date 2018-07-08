package com.amitk.springboot.rest.util.error;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomAPIError {
	private String timestamp; 
	private HttpStatus status;
	private String message;
	private List<String> errors;

	public CustomAPIError(String timestamp,HttpStatus status, String message, List<String> errors) {
		super();
		this.timestamp=timestamp;
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public CustomAPIError(String timestamp,HttpStatus status, String message, String error) {
		super();
		this.timestamp=timestamp;
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}