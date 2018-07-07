/**
 * 
 */
package com.amitk.springboot.rest.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ FIELD, METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  NameValidator.class)
/**
 * @author amitk
 *
 */
public @interface Name {
	String message() default "Invalid Name, must contain alphabet only";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
