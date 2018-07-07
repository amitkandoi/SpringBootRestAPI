/**
 * 
 */
package com.amitk.springboot.rest.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author amitk
 *
 */
public class NameValidator implements ConstraintValidator<Name, String> {

	public static final Logger LOGGER = LoggerFactory.getLogger(NameValidator.class);

	@Override
	public boolean isValid(String name, ConstraintValidatorContext cvc) {
		LOGGER.info("validating name");
		return name.matches("^[a-zA-Z .-]*$");
	}

}
