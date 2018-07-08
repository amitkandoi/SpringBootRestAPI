package com.amitk.springboot.rest.util.error;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger LOGGER = LoggerFactory.getLogger(CustomRestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOGGER.info("Inside method handleMethodArgumentNotValid");
		List<String> errors = new ArrayList<String>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, customAPIError, headers, customAPIError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + ", "));

		CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(),
				builder.toString());
		return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		LOGGER.info("404 error");
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		final CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
	}

	@ExceptionHandler({CustomException.class})
    public final ResponseEntity<Object> handleCustomError(CustomException ex, WebRequest request) {
		LOGGER.info("inside Custom Error Handler");
		final String error = ex.getLocalizedMessage();
        CustomAPIError customAPIError=new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.NOT_FOUND,"Please Correct Below Error.", error);
        return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
    }
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
				"error occurred");
		return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		LOGGER.info("Inside method handleConstraintViolation");
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
	}

	@Override
	public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		LOGGER.info("Inside method handleMissingPathVariable");
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getMessage());
		errors.add(ex.getVariableName());
		errors.add(ex.getVariableName());
		CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		LOGGER.info("Inside method handleMethodArgumentTypeMismatch");
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		CustomAPIError customAPIError = new CustomAPIError(new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date()),HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(customAPIError, new HttpHeaders(), customAPIError.getStatus());
	}
}
